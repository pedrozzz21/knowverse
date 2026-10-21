/**
 * ============================================================================
 * KNOWVERSE - SISTEMA DE ATUALIZAÇÃO OVER-THE-AIR (OTA) & NOTIFICAÇÕES DEV
 * ============================================================================
 * 
 * GUIA DE INSTALAÇÃO E CONFIGURAÇÃO (Execute no seu terminal):
 * 
 * 1. Instale o pacote de atualizações do Expo:
 *    npx expo install expo-updates
 * 
 * 2. Instale o pacote de constantes do Expo:
 *    npx expo install expo-constants
 * 
 * 3. Instale o Firebase (se ainda não tiver no seu projeto Expo):
 *    npx expo install firebase
 * 
 * 4. No seu `app.json` (ou `app.config.js`), certifique-se de configurar:
 *    {
 *      "expo": {
 *        "updates": {
 *          "url": "https://u.expo.dev/SEU_PROJECT_ID_AQUI",
 *          "enabled": true,
 *          "checkAutomatically": "NEVER" // Usaremos verificação controlada via código!
 *        },
 *        "runtimeVersion": {
 *          "policy": "appVersion"
 *        }
 *      }
 *    }
 * ============================================================================
 */

import React, { useState, useEffect } from 'react';
import {
  Modal,
  View,
  Text,
  TouchableOpacity,
  ActivityIndicator,
  StyleSheet,
  Alert
} from 'react-native';
import * as Updates from 'expo-updates';
import Constants from 'expo-constants';
import { 
  getFirestore, 
  doc, 
  getDoc, 
  collection, 
  addDoc, 
  serverTimestamp 
} from 'firebase/firestore';

// Versão atual do aplicativo instalada no dispositivo do usuário
const CURRENT_APP_VERSION = Constants.expoConfig?.version || '1.0.0';

/**
 * Modal de Atualização Silenciosa OTA para o Knowverse.
 *
 * @param {Object} props
 * @param {string} props.currentUserRole - Papel do usuário ('dev' ou 'student').
 * @param {string} props.currentUserEmail - E-mail do usuário autenticado.
 */
export default function OtaUpdateModal({ currentUserRole = 'student', currentUserEmail = '' }) {
  // Estados para controle da interface e do processo de download
  const [modalVisible, setModalVisible] = useState(false);
  const [isDownloading, setIsDownloading] = useState(false);
  const [updateInfo, setUpdateInfo] = useState({
    latestVersion: '',
    releaseNotes: 'Melhorias de desempenho e novos conteúdos liberados!',
    isMandatory: false
  });

  // Identifica se a conta possui privilégio de desenvolvedor
  const isDevUser = currentUserRole === 'dev' || 
    currentUserEmail?.toLowerCase() === 'contatopedro21g@gmail.com';

  // Ao montar o componente, verifica se há atualização no Firebase
  useEffect(() => {
    checkForAppUpdate();
  }, []);

  /**
   * 1. Consulta o Firestore no documento 'app_config/versioning'
   */
  const checkForAppUpdate = async () => {
    try {
      if (__DEV__) {
        console.log('[OTA] Modo de desenvolvimento detectado. Atualizações OTA reais requerem build EAS.');
      }

      const db = getFirestore();
      const versionDocRef = doc(db, 'app_config', 'versioning');
      const docSnap = await getDoc(versionDocRef);

      if (docSnap.exists()) {
        const data = docSnap.data();
        const serverVersion = data.latestVersion || '1.0.0';

        // Compara a versão do servidor com a versão local
        if (isVersionGreater(serverVersion, CURRENT_APP_VERSION)) {
          setUpdateInfo({
            latestVersion: serverVersion,
            releaseNotes: data.releaseNotes || 'Nova versão disponível para você!',
            isMandatory: data.isMandatory ?? false
          });
          setModalVisible(true);
        }
      }
    } catch (error) {
      console.warn('[OTA] Erro ao consultar versão no Firestore:', error);
    }
  };

  /**
   * Função utilitária para comparar versões semânticas (ex: "1.0.2" > "1.0.1")
   */
  const isVersionGreater = (remoteVer, currentVer) => {
    const remoteParts = remoteVer.split('.').map(Number);
    const currentParts = currentVer.split('.').map(Number);

    for (let i = 0; i < Math.max(remoteParts.length, currentParts.length); i++) {
      const r = remoteParts[i] || 0;
      const c = currentParts[i] || 0;
      if (r > c) return true;
      if (r < c) return false;
    }
    return false;
  };

  /**
   * 2. Lógica do botão "Atualizar Agora" usando Expo Updates OTA
   */
  const handleApplyUpdate = async () => {
    setIsDownloading(true);

    try {
      // Simulação para o ambiente de testes em Expo Go / Dev Client
      if (__DEV__) {
        setTimeout(async () => {
          setIsDownloading(false);
          if (isDevUser) {
            await broadcastUpdateNotification();
          }
          Alert.alert(
            "Modo Dev Simulado",
            "Simulação concluída! Em builds de produção EAS, o app baixa o código e reinicia instantaneamente."
          );
          setModalVisible(false);
        }, 1200);
        return;
      }

      // Passo A: Verifica no servidor do Expo se há um bundle novo publicado
      const checkResult = await Updates.checkForUpdateAsync();

      if (checkResult.isAvailable) {
        // Passo B: Baixa o novo pacote JavaScript/Assets silenciosamente
        await Updates.fetchUpdateAsync();

        // Se o usuário atual for desenvolvedor ('dev'), dispara a notificação global
        if (isDevUser) {
          await broadcastUpdateNotification();
        }

        // Passo C: Reinicia o app instantaneamente com o código novo
        await Updates.reloadAsync();
      } else {
        Alert.alert(
          "Tudo atualizado!",
          "Você já está na versão mais recente disponível no servidor."
        );
        setModalVisible(false);
      }
    } catch (error) {
      console.error('[OTA] Erro durante o download da atualização:', error);
      Alert.alert(
        "Falha ao Atualizar",
        "Não foi possível baixar a atualização. Verifique sua conexão com a internet e tente novamente."
      );
    } finally {
      setIsDownloading(false);
    }
  };

  /**
   * 3. Dispara notificação push para todos os usuários quando uma conta 'dev' atualiza
   */
  const broadcastUpdateNotification = async () => {
    try {
      const db = getFirestore();
      await addDoc(collection(db, 'broadcast_notifications'), {
        title: "Nova Atualização Disponível! Bora estudar!! 🔥🔥",
        body: updateInfo.releaseNotes,
        target: "all_users",
        sentByRole: "dev",
        createdAt: serverTimestamp(),
      });
      console.log('[DEV] Notificação global de atualização registrada com sucesso no Firestore.');
    } catch (err) {
      console.warn('[DEV] Erro ao gravar notificação no Firestore:', err);
    }
  };

  return (
    <Modal
      transparent={true}
      visible={modalVisible}
      animationType="fade"
      onRequestClose={() => {
        if (!updateInfo.isMandatory && !isDownloading) {
          setModalVisible(false);
        }
      }}
    >
      <View style={styles.overlay}>
        <View style={styles.modalCard}>
          {/* Badge de Versão */}
          <View style={styles.badgeContainer}>
            <Text style={styles.badgeText}>v{updateInfo.latestVersion}</Text>
          </View>

          {/* Título */}
          <Text style={styles.title}>Atualização Disponível</Text>

          {/* Descrição / Notas da Versão */}
          <Text style={styles.message}>
            {updateInfo.releaseNotes}
          </Text>

          {/* Botão Primário de Ação */}
          <TouchableOpacity
            style={[styles.buttonPrimary, isDownloading && styles.buttonDisabled]}
            onPress={handleApplyUpdate}
            disabled={isDownloading}
            activeOpacity={0.8}
          >
            {isDownloading ? (
              <View style={styles.loadingRow}>
                <ActivityIndicator size="small" color="#FFFFFF" />
                <Text style={styles.buttonText}>Baixando atualização...</Text>
              </View>
            ) : (
              <Text style={styles.buttonText}>Atualizar Agora</Text>
            )}
          </TouchableOpacity>

          {/* Botão de Fechar (se a atualização não for obrigatória) */}
          {!updateInfo.isMandatory && !isDownloading && (
            <TouchableOpacity
              style={styles.buttonSecondary}
              onPress={() => setModalVisible(false)}
            >
              <Text style={styles.buttonSecondaryText}>Lembrar Mais Tarde</Text>
            </TouchableOpacity>
          )}
        </View>
      </View>
    </Modal>
  );
}

// ============================================================================
// ESTILIZAÇÃO DARK MODE (Design System do Knowverse: Neutros e Acentos Limpos)
// ============================================================================
const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.75)',
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 20
  },
  modalCard: {
    width: '100%',
    maxWidth: 340,
    backgroundColor: '#11141B', // ObsidianSurface (Zinc 900)
    borderRadius: 16,
    padding: 20,
    borderWidth: 1,
    borderColor: '#232834',     // 1px BorderSubtle
    alignItems: 'center'
  },
  badgeContainer: {
    backgroundColor: 'rgba(59, 130, 246, 0.15)',
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 6,
    marginBottom: 10
  },
  badgeText: {
    color: '#3B82F6',           // PrimaryViolet / Azul Elétrico
    fontSize: 12,
    fontWeight: '700'
  },
  title: {
    fontSize: 18,
    fontWeight: '700',
    color: '#F8FAFC',           // TextPrimary
    marginBottom: 8,
    textAlign: 'center'
  },
  message: {
    fontSize: 13,
    color: '#94A3B8',           // TextSecondary
    lineHeight: 18,
    textAlign: 'center',
    marginBottom: 20
  },
  buttonPrimary: {
    width: '100%',
    height: 44,
    backgroundColor: '#3B82F6', // Azul elétrico do Knowverse
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center'
  },
  buttonDisabled: {
    opacity: 0.6
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 14,
    fontWeight: '700'
  },
  loadingRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8
  },
  buttonSecondary: {
    marginTop: 12,
    paddingVertical: 6
  },
  buttonSecondaryText: {
    color: '#64748B',           // TextTertiary
    fontSize: 12,
    fontWeight: '500'
  }
});
