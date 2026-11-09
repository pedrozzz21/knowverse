const http = require('http');
const fs = require('fs');
const path = require('path');
const url = require('url');

const PORT = parseInt(process.env.DEFAULT_APP_PORT || process.env.APP_PORT || '3000', 10);
const APP_DIR = __dirname;
const PUBLIC_DIR = path.join(APP_DIR, 'public');

const MIME_TYPES = {
  '.html': 'text/html; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.js': 'application/javascript; charset=utf-8',
  '.json': 'application/json; charset=utf-8',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.svg': 'image/svg+xml',
  '.ico': 'image/x-icon',
  '.apk': 'application/vnd.android.package-archive',
  '.zip': 'application/zip',
  '.pdf': 'application/pdf',
  '.txt': 'text/plain; charset=utf-8'
};

function resolveApkPath() {
  const candidates = [
    path.join(PUBLIC_DIR, 'Knowverse-v2.0.0.apk'),
    path.join(PUBLIC_DIR, 'Knowverse.apk'),
    path.join(APP_DIR, 'Knowverse.apk'),
    path.join(APP_DIR, 'knowverse.apk'),
    path.join(APP_DIR, 'app/build/outputs/apk/debug/app-debug.apk'),
    path.join(PUBLIC_DIR, 'knowverse.apk')
  ];
  for (const candidate of candidates) {
    if (fs.existsSync(candidate)) {
      return candidate;
    }
  }
  return null;
}

function resolveZipPath() {
  const candidates = [
    path.join(PUBLIC_DIR, 'Knowverse-v2.0.0.zip'),
    path.join(PUBLIC_DIR, 'knowverse_apk.zip'),
    path.join(APP_DIR, 'knowverse_apk.zip'),
    path.join(APP_DIR, 'Knowverse.zip'),
    path.join(PUBLIC_DIR, 'Knowverse.zip')
  ];
  for (const candidate of candidates) {
    if (fs.existsSync(candidate)) {
      return candidate;
    }
  }
  return null;
}

function serveStreamFile(req, res, filePath, contentType, attachmentName) {
  try {
    const stat = fs.statSync(filePath);
    const totalSize = stat.size;
    const rangeHeader = req.headers.range;

    const baseHeaders = {
      'Content-Type': contentType,
      'Accept-Ranges': 'bytes',
      'Cache-Control': 'no-cache, no-store, must-revalidate',
      'Access-Control-Allow-Origin': '*',
      'Last-Modified': stat.mtime.toUTCString()
    };

    if (attachmentName) {
      baseHeaders['Content-Disposition'] = `attachment; filename="${attachmentName}"; filename*=UTF-8''${encodeURIComponent(attachmentName)}`;
    }

    if (req.method === 'HEAD') {
      baseHeaders['Content-Length'] = totalSize;
      res.writeHead(200, baseHeaders);
      res.end();
      return;
    }

    if (rangeHeader) {
      const parts = rangeHeader.replace(/bytes=/, '').split('-');
      const start = parseInt(parts[0], 10);
      const end = parts[1] ? parseInt(parts[1], 10) : totalSize - 1;

      if (isNaN(start) || start >= totalSize || end >= totalSize || start > end) {
        res.writeHead(416, {
          'Content-Range': `bytes */${totalSize}`,
          'Content-Type': 'text/plain'
        });
        res.end('Requested Range Not Satisfiable');
        return;
      }

      const chunkSize = end - start + 1;
      const rangeHeaders = Object.assign({}, baseHeaders, {
        'Content-Range': `bytes ${start}-${end}/${totalSize}`,
        'Content-Length': chunkSize
      });

      res.writeHead(206, rangeHeaders);
      const stream = fs.createReadStream(filePath, { start, end });
      stream.pipe(res);
    } else {
      baseHeaders['Content-Length'] = totalSize;
      res.writeHead(200, baseHeaders);
      const stream = fs.createReadStream(filePath);
      stream.pipe(res);
    }
  } catch (err) {
    console.error(`Error serving ${filePath}:`, err);
    res.writeHead(500, { 'Content-Type': 'text/plain' });
    res.end('Internal Server Error: Unable to read file');
  }
}

const server = http.createServer((req, res) => {
  const parsedUrl = url.parse(req.url, true);
  const pathname = decodeURIComponent(parsedUrl.pathname);

  // Health and status checks
  if (pathname === '/health' || pathname === '/dev/status') {
    res.writeHead(200, {
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache',
      'Access-Control-Allow-Origin': '*'
    });
    res.end(JSON.stringify({
      status: 'ok',
      app: 'Knowverse',
      version: '2.0.0',
      build: 20,
      apkReady: resolveApkPath() !== null,
      zipReady: resolveZipPath() !== null,
      timestamp: new Date().toISOString()
    }));
    return;
  }

  // AI Studio iframe helper script
  if (pathname === '/_aistudio-iframe.js') {
    const iframeAssetPath = '/var/www/assets/_aistudio-iframe.js';
    if (fs.existsSync(iframeAssetPath)) {
      serveStreamFile(req, res, iframeAssetPath, 'application/javascript; charset=utf-8');
    } else {
      res.writeHead(200, { 'Content-Type': 'application/javascript; charset=utf-8' });
      res.end('// AI Studio Iframe bridge');
    }
    return;
  }

  // APK download routes
  if (/\.apk$/i.test(pathname)) {
    const apkPath = resolveApkPath();
    if (apkPath) {
      serveStreamFile(req, res, apkPath, 'application/vnd.android.package-archive', 'Knowverse-v2.0.0.apk');
    } else {
      res.writeHead(404, { 'Content-Type': 'text/html; charset=utf-8' });
      res.end('<h1>APK em compilação</h1><p>O arquivo está sendo gerado. Por favor, aguarde alguns instantes e atualize a página.</p>');
    }
    return;
  }

  // ZIP download routes
  if (/\.zip$/i.test(pathname)) {
    const zipPath = resolveZipPath();
    if (zipPath) {
      serveStreamFile(req, res, zipPath, 'application/zip', 'Knowverse-v2.0.0.zip');
    } else {
      res.writeHead(404, { 'Content-Type': 'text/html; charset=utf-8' });
      res.end('<h1>Pacote ZIP não encontrado</h1><p>Por favor, recarregue a página em alguns instantes.</p>');
    }
    return;
  }

  // Installer HTML routes (Root, /download, /baixar, /installer, /index.html)
  if (pathname === '/' || pathname === '/index.html' || pathname === '/download' || pathname === '/download.html' || pathname === '/baixar' || pathname === '/installer') {
    const indexPath = path.join(PUBLIC_DIR, 'index.html');
    if (fs.existsSync(indexPath)) {
      serveStreamFile(req, res, indexPath, 'text/html; charset=utf-8');
      return;
    }
  }

  // Static files in public directory
  const safePath = path.normalize(pathname).replace(/^(\.\.[\/\\])+/, '');
  const candidatePublicFile = path.join(PUBLIC_DIR, safePath);

  if (fs.existsSync(candidatePublicFile) && fs.statSync(candidatePublicFile).isFile()) {
    const ext = path.extname(candidatePublicFile).toLowerCase();
    const contentType = MIME_TYPES[ext] || 'application/octet-stream';
    serveStreamFile(req, res, candidatePublicFile, contentType);
    return;
  }

  // Fallback to index.html for any unhandled routes
  const indexPath = path.join(PUBLIC_DIR, 'index.html');
  if (fs.existsSync(indexPath)) {
    serveStreamFile(req, res, indexPath, 'text/html; charset=utf-8');
  } else {
    res.writeHead(404, { 'Content-Type': 'text/plain' });
    res.end('404 Not Found');
  }
});

server.listen(PORT, '0.0.0.0', () => {
  console.log(`Knowverse Web Server listening on port ${PORT}`);
  console.log(`Public dir: ${PUBLIC_DIR}`);
  console.log(`APK path: ${resolveApkPath()}`);
  console.log(`ZIP path: ${resolveZipPath()}`);
});
