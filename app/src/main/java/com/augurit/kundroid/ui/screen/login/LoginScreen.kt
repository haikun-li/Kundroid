package com.augurit.kundroid.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.augurit.kundroid.R
import com.augurit.kundroid.data.Resource
import com.augurit.kundroid.ui.commonCompose.LoadingDialog
import com.augurit.kundroid.ui.commonCompose.MainButton


//登录页
@Composable
fun LoginScreen(navController: NavHostController,viewModel: LoginViewModel= hiltViewModel() ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxHeight()) {
        val loginState by viewModel.login.collectAsState(initial = Resource.EmptyResource())
        if (loginState is Resource.LoadingResource){
            LoadingDialog()
        }
        //顶部图片
        LoginTop()
        //输入框和按钮
        LoginCenter { account, password, rememberPassword ->
            viewModel.login(account, password, rememberPassword,navController)
        }
    }
}

///输入框和按钮
@Composable
fun LoginCenter(login: (account: String, password: String, rememberPassword: Boolean) -> Unit) {
    var account by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var rememberPassword by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout {
        val (accountTextField, passwordTextField, remember) = createRefs()

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(accountTextField) {
                    top.linkTo(parent.top, margin = 30.dp)
                },
            value = account,
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(R.string.account))
            },
            onValueChange = {
                account = it
            })

        OutlinedTextField(
            value = password,
            modifier = Modifier
                .padding(top = 16.dp)
                .constrainAs(passwordTextField) {
                    top.linkTo(accountTextField.bottom, margin = 0.dp)
                },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(R.string.password))
            },
            onValueChange = {
                password = it
            })

        Row(modifier = Modifier
            .clickable {
                rememberPassword = !rememberPassword
            }
            .constrainAs(remember) {
                top.linkTo(passwordTextField.bottom, margin = 32.dp)
                start.linkTo(passwordTextField.start, margin = 0.dp)
            }) {
            Checkbox(checked = rememberPassword, onCheckedChange = {
                rememberPassword = !rememberPassword
            })

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.remeber_password)
            )
        }

    }

    MainButton(onClick = {
        login(account, password, rememberPassword)
    }, text = stringResource(R.string.login), modifier = Modifier.padding(top = 32.dp))

}

//顶部图片
@Composable
fun LoginTop() {
    Box {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.bg_login),
            contentDescription = null
        )
    }
}
