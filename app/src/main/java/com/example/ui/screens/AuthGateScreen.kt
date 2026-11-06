package com.example.ui.screens

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.auth.AuthManager
import com.example.ui.components.KnowverseIcons
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun AuthGateScreen(
    authManager: AuthManager
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val isSigningIn by authManager.isSigningIn.collectAsState()
    var localErrorMessage by remember { mutableStateOf<String?>(null) }
    var activeTab by remember { mutableIntStateOf(0) } // 0: Entrar, 1: Cadastrar

    // Login Form State
    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }
    var showLoginPassword by remember { mutableStateOf(false) }

    // Register Form State
    var registerName by remember { mutableStateOf("") }
    var registerEmail by remember { mutableStateOf("") }
    var registerPassword by remember { mutableStateOf("") }
    var showRegisterPassword by remember { mutableStateOf(false) }
    var selectedQuestionIndex by remember { mutableIntStateOf(0) }
    var showQuestionDropdown by remember { mutableStateOf(false) }
    var registerAnswer by remember { mutableStateOf("") }

    // Forgot Password Flow State
    var showForgotPasswordDialog by remember { mutableStateOf(false) }
    var forgotEmail by remember { mutableStateOf("") }
    var forgotQuestion by remember { mutableStateOf<String?>(null) }
    var forgotAnswer by remember { mutableStateOf("") }
    var generatedAccessToken by remember { mutableStateOf<String?>(null) }
    var forgotErrorMessage by remember { mutableStateOf<String?>(null) }

    // Container centralizado vertical e horizontalmente para todas as telas (Mobile, Dobráveis e Tablets)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 460.dp)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ========================================================
            // CABEÇALHO HERO CENTRALIZADO COM MASCOTE OTTO & BRANDING
            // ========================================================
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(SurfaceElevated)
                    .border(2.dp, PrimaryViolet.copy(alpha = 0.6f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                    contentDescription = "Mascote Otto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Knowverse",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        fontSize = 26.sp,
                        letterSpacing = (-0.5).sp
                    )
                )

                Surface(
                    color = PrimaryViolet.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.35f))
                ) {
                    Text(
                        text = "STUDY HUB",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = PrimaryViolet,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp,
                            letterSpacing = 0.6.sp
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Sua plataforma completa de preparação para o ENEM e Programação com didática visual e trilhas guiadas.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp,
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Chips das Disciplinas Principais
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val tags = listOf("Matemática", "Física", "Química", "Biologia", "Redação", "Python")
                Text(
                    text = tags.joinToString(" • "),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextTertiary,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ========================================================
            // CARD DE AUTENTICAÇÃO CENTRALIZADO COM TABS INTEGRADAS
            // ========================================================
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Switcher Tabs: Entrar / Cadastrar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceElevated)
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            .padding(3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (activeTab == 0) PrimaryViolet else Color.Transparent)
                                .clickable {
                                    activeTab = 0
                                    localErrorMessage = null
                                }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Entrar",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (activeTab == 0) Color.White else TextSecondary,
                                    fontSize = 13.sp
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (activeTab == 1) PrimaryViolet else Color.Transparent)
                                .clickable {
                                    activeTab = 1
                                    localErrorMessage = null
                                }
                                .padding(vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Cadastrar",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (activeTab == 1) Color.White else TextSecondary,
                                    fontSize = 13.sp
                                )
                            )
                        }
                    }

                    // Global/Local Error banner
                    if (localErrorMessage != null) {
                        Surface(
                            color = ErrorRose.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, ErrorRose.copy(alpha = 0.35f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = localErrorMessage ?: "",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = ErrorRose,
                                    textAlign = TextAlign.Center,
                                    fontSize = 11.5.sp
                                ),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                    if (activeTab == 0) {
                        // ==============================
                        // TAB 0: ENTRAR (LOGIN)
                        // ==============================
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            OutlinedTextField(
                                value = loginEmail,
                                onValueChange = { loginEmail = it },
                                label = { Text("E-mail") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("login_email_input")
                            )

                            OutlinedTextField(
                                value = loginPassword,
                                onValueChange = { loginPassword = it },
                                label = { Text("Senha") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { showLoginPassword = !showLoginPassword }) {
                                        Icon(
                                            imageVector = if (showLoginPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                            contentDescription = null,
                                            tint = TextSecondary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                },
                                visualTransformation = if (showLoginPassword) VisualTransformation.None else PasswordVisualTransformation(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("login_password_input")
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = {
                                        forgotEmail = loginEmail.trim()
                                        forgotQuestion = null
                                        forgotAnswer = ""
                                        generatedAccessToken = null
                                        forgotErrorMessage = null
                                        showForgotPasswordDialog = true
                                    },
                                    modifier = Modifier.testTag("forgot_password_btn")
                                ) {
                                    Text(
                                        text = "Esqueci a Senha",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = PrimaryViolet,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }

                            Button(
                                onClick = {
                                    localErrorMessage = null
                                    authManager.signInWithEmail(
                                        email = loginEmail.trim(),
                                        password = loginPassword,
                                        onSuccess = {},
                                        onError = { localErrorMessage = it }
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryViolet,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("email_sign_in_btn")
                            ) {
                                Text(
                                    text = "Entrar com E-mail e Senha",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1f).height(1.dp).background(BorderSubtle))
                                Text(
                                    text = "ou continue com",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextTertiary,
                                        fontSize = 10.5.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                                Box(modifier = Modifier.weight(1f).height(1.dp).background(BorderSubtle))
                            }

                            // Google Sign In (Primary SSO)
                            Button(
                                onClick = {
                                    if (activity != null) {
                                        localErrorMessage = null
                                        authManager.signInWithGoogle(activity, scope, onError = { localErrorMessage = it })
                                    }
                                },
                                enabled = !isSigningIn,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SurfaceElevated,
                                    contentColor = TextPrimary
                                ),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("google_sign_in_btn")
                            ) {
                                if (isSigningIn) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(18.dp),
                                        strokeWidth = 2.dp,
                                        color = Color.White
                                    )
                                } else {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = KnowverseIcons.UserSilhouette,
                                            contentDescription = null,
                                            tint = PrimaryViolet,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Continuar com Conta Google",
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        // ==============================
                        // TAB 1: CADASTRAR (REGISTRO)
                        // ==============================
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            OutlinedTextField(
                                value = registerName,
                                onValueChange = { registerName = it },
                                label = { Text("Nome Completo") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("register_name_input")
                            )

                            OutlinedTextField(
                                value = registerEmail,
                                onValueChange = { registerEmail = it },
                                label = { Text("E-mail") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("register_email_input")
                            )

                            OutlinedTextField(
                                value = registerPassword,
                                onValueChange = { registerPassword = it },
                                label = { Text("Senha (mínimo 6 caracteres)") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { showRegisterPassword = !showRegisterPassword }) {
                                        Icon(
                                            imageVector = if (showRegisterPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                            contentDescription = null,
                                            tint = TextSecondary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                },
                                visualTransformation = if (showRegisterPassword) VisualTransformation.None else PasswordVisualTransformation(),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("register_password_input")
                            )

                            // Pergunta Pessoal de Segurança para recuperação de conta
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Pergunta de Segurança (para recuperação):",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TextSecondary,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 11.sp
                                    )
                                )

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Surface(
                                        color = SurfaceElevated,
                                        shape = RoundedCornerShape(10.dp),
                                        border = BorderStroke(1.dp, BorderSubtle),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { showQuestionDropdown = true }
                                            .padding(horizontal = 12.dp, vertical = 10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = AuthManager.SECURITY_QUESTIONS[selectedQuestionIndex],
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    color = TextPrimary,
                                                    fontSize = 12.sp
                                                ),
                                                modifier = Modifier.weight(1f)
                                            )
                                            Icon(
                                                imageVector = Icons.Default.ArrowDropDown,
                                                contentDescription = null,
                                                tint = TextSecondary
                                            )
                                        }
                                    }

                                    DropdownMenu(
                                        expanded = showQuestionDropdown,
                                        onDismissRequest = { showQuestionDropdown = false },
                                        modifier = Modifier.background(ObsidianSurface)
                                    ) {
                                        AuthManager.SECURITY_QUESTIONS.forEachIndexed { index, question ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = question,
                                                        style = MaterialTheme.typography.bodySmall.copy(color = TextPrimary)
                                                    )
                                                },
                                                onClick = {
                                                    selectedQuestionIndex = index
                                                    showQuestionDropdown = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            OutlinedTextField(
                                value = registerAnswer,
                                onValueChange = { registerAnswer = it },
                                label = { Text("Sua Resposta Secreta") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.HelpOutline,
                                        contentDescription = null,
                                        tint = TextSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PrimaryViolet,
                                    unfocusedBorderColor = BorderSubtle,
                                    focusedTextColor = TextPrimary,
                                    unfocusedTextColor = TextPrimary
                                ),
                                modifier = Modifier.fillMaxWidth().testTag("register_answer_input")
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Button(
                                onClick = {
                                    localErrorMessage = null
                                    authManager.signUpWithEmail(
                                        email = registerEmail.trim(),
                                        password = registerPassword,
                                        displayName = registerName.trim(),
                                        securityQuestion = AuthManager.SECURITY_QUESTIONS[selectedQuestionIndex],
                                        securityAnswer = registerAnswer.trim(),
                                        onSuccess = {},
                                        onError = { localErrorMessage = it }
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryViolet,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("create_account_btn")
                            ) {
                                Text(
                                    text = "Criar Minha Conta Gratuita",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        }
                    }
                }
            }


        }
    }

    // ========================================================
    // MODAL DIALOG: ESQUECI A SENHA COM RESPOSTA PESSOAL E TOKEN
    // ========================================================
    if (showForgotPasswordDialog) {
        AlertDialog(
            onDismissRequest = { showForgotPasswordDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Text(
                    text = "Recuperação de Senha",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (forgotErrorMessage != null) {
                        Surface(
                            color = ErrorRose.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = forgotErrorMessage ?: "",
                                style = MaterialTheme.typography.labelSmall.copy(color = ErrorRose),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    if (generatedAccessToken != null) {
                        // Access token generated!
                        Surface(
                            color = SuccessEmerald.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Identidade confirmada!",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = SuccessEmerald,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Seu token de acesso provisório foi gerado. Clique abaixo para entrar e redefinir sua senha com tranquilidade no seu Perfil.",
                                    style = MaterialTheme.typography.bodySmall.copy(color = TextPrimary)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Surface(
                                    color = DeepNavy,
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = generatedAccessToken ?: "",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = GoldXp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 2.sp,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    } else if (forgotQuestion == null) {
                        // Step 1: Input email to fetch security question
                        Text(
                            text = "Digite o e-mail cadastrado para localizar sua pergunta de segurança:",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        OutlinedTextField(
                            value = forgotEmail,
                            onValueChange = { forgotEmail = it },
                            label = { Text("E-mail cadastrado") },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryViolet,
                                unfocusedBorderColor = BorderSubtle,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        // Step 2: Answer the security question
                        Text(
                            text = "Responda à sua pergunta cadastrada:",
                            style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                        )

                        Surface(
                            color = SurfaceElevated,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = forgotQuestion ?: "",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                        OutlinedTextField(
                            value = forgotAnswer,
                            onValueChange = { forgotAnswer = it },
                            label = { Text("Sua resposta") },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryViolet,
                                unfocusedBorderColor = BorderSubtle,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                if (generatedAccessToken != null) {
                    Button(
                        onClick = {
                            authManager.signInWithAccessToken(
                                email = forgotEmail,
                                token = generatedAccessToken ?: "",
                                onSuccess = { showForgotPasswordDialog = false },
                                onError = { forgotErrorMessage = it }
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessEmerald)
                    ) {
                        Text("Entrar com Token de Acesso")
                    }
                } else if (forgotQuestion == null) {
                    Button(
                        onClick = {
                            forgotErrorMessage = null
                            val q = authManager.getSecurityQuestionForEmail(forgotEmail)
                            if (q != null) {
                                forgotQuestion = q
                            } else {
                                forgotErrorMessage = "Nenhuma conta cadastrada encontrada para este e-mail."
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                    ) {
                        Text("Localizar Pergunta")
                    }
                } else {
                    Button(
                        onClick = {
                            forgotErrorMessage = null
                            authManager.requestPasswordReset(
                                email = forgotEmail,
                                securityAnswer = forgotAnswer,
                                onSuccessToken = { generatedAccessToken = it },
                                onError = { forgotErrorMessage = it }
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                    ) {
                        Text("Validar Resposta")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showForgotPasswordDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}
