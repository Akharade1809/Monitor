package org.monitor.app.ui.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import org.monitor.app.R
import org.monitor.app.ui.components.ManagementResourceState
import org.monitor.app.ui.theme.designSystem.InputTextField
import org.monitor.app.ui.theme.designSystem.MainTopBar
import org.monitor.app.ui.theme.designSystem.SubmitButton
import org.monitor.shared.features.student.AddStudentContract
import org.monitor.shared.features.student.AddStudentInfoModel

@Composable
fun AddStudentScreen(
    navController: NavController,
    onUiEvent: (AddStudentContract.Event) -> Unit,
    uiState: StateFlow<AddStudentContract.State>,
    uiEffect: Flow<AddStudentContract.Effect>
){
    val state by uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        uiEffect.collectLatest { effect->
            when(effect){
                AddStudentContract.Effect.BackNavigation -> navController.popBackStack()
                AddStudentContract.Effect.NavigateOnSuccessfulAddition -> {
                    //TODO
                }
                AddStudentContract.Effect.NavigateToStudentListPage-> {
                    //TODO
                }
            }
        }
    }

    ManagementResourceState(
        resourceState = state.addStudentState,
        successView = { addStudentInfoModel ->
            if (addStudentInfoModel == null) return@ManagementResourceState
            Scaffold(
                topBar = {
                    MainTopBar(
                        "Add a new Student",
                        ""
                    )
                }
            ) { padding ->
                val scrollState: ScrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentScale = ContentScale.Inside,
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AddStudentContent(
                            modifier = Modifier,
                            onUiEvent = onUiEvent,
                            uiInfo = addStudentInfoModel
                        )
                    }
                }
            }

        },
        onTryAgain = { },

    )
}

@Composable
private fun AddStudentContent(
    modifier: Modifier,
    onUiEvent: (AddStudentContract.Event) -> Unit,
    uiInfo : AddStudentInfoModel?,
){
    val focusManager = LocalFocusManager.current
    val configuration = LocalConfiguration.current

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var mobileNumber by rememberSaveable { mutableStateOf("") }
    var whatsappNumber by rememberSaveable { mutableStateOf("") }
    var schoolName by rememberSaveable { mutableStateOf("") }
    var studentClass by rememberSaveable { mutableStateOf(0.0) }

    var isFirstNameValid by rememberSaveable { mutableStateOf(true)}
    var isLastNameValid by rememberSaveable { mutableStateOf(true)}
    var isMobileNumberValid by rememberSaveable { mutableStateOf(true)}
    var isWhatsappNumberValid by rememberSaveable { mutableStateOf(true)}

    val formEditObject = AddStudentContract.Event.OnFormEdited(
        firstName = firstName,
        lastName = lastName,
        mobileNumber = mobileNumber,
        whatsappNumber = whatsappNumber,
        schoolName = schoolName,
        studentClass = studentClass
    )
    Spacer(modifier = Modifier.height(32.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        InputTextField(
            value = firstName,
            onValueChange = {
                firstName = it
                isFirstNameValid = it.isNotBlank()
                onUiEvent(
                    formEditObject.copy(firstName = firstName)
                )
            },
            label = "First Name",
            placeholder = "First Name",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (firstName.isEmpty()) .5f else 1f),
            isError = !isFirstNameValid,
            errorMsg = "First Name is not Valid"
        )
        Spacer(modifier = Modifier.height(24.dp))

        InputTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                isLastNameValid = it.isNotBlank()
                onUiEvent(
                    formEditObject.copy(lastName = lastName)
                )
            },
            label = "Last Name",
            placeholder = "Last Name",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (lastName.isEmpty()) .5f else 1f),
            isError = !isLastNameValid,
            errorMsg = "last Name is not Valid"
        )
        Spacer(modifier = Modifier.height(24.dp))

//        InputTextField(
//            value = studentClass.toString(),
//            onValueChange = {
//                middleName = it
//                onUiEvent(
//                    formEditObject.copy(middleName = middleName)
//                )
//            },
//            label = "Middle Name",
//            placeholder = "Middle Name",
//            modifier = Modifier
//                .fillMaxWidth()
//                .alpha(if (middleName.isEmpty()) .5f else 1f),
//        )
//        Spacer(modifier = Modifier.height(24.dp))


        InputTextField(
            value = mobileNumber,
            onValueChange = {
                mobileNumber = it
                isMobileNumberValid = it.isNotBlank() && mobileNumber.length == 10
                onUiEvent(
                    formEditObject.copy(mobileNumber = mobileNumber)
                )
            },
            label = "Mobile number +91",
            placeholder = "Mobile Number +91",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (mobileNumber.isEmpty()) .5f else 1f),
            isError = !isMobileNumberValid,
            errorMsg = "please check the mobile number"
        )
        Spacer(modifier = Modifier.height(24.dp))

        InputTextField(
            value = whatsappNumber,
            onValueChange = {
                whatsappNumber = it
                isWhatsappNumberValid = it.isNotBlank() && whatsappNumber.length == 10
                onUiEvent(
                    formEditObject.copy(whatsappNumber = whatsappNumber)
                )
            },
            label = "Whatsapp Number",
            placeholder = "Whatsapp Number",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (whatsappNumber.isEmpty()) .5f else 1f),
            isError = !isWhatsappNumberValid,
            errorMsg = "please check the whatsapp number"
        )
        Spacer(modifier = Modifier.height(24.dp))

        InputTextField(
            value = schoolName,
            onValueChange = {
                schoolName = it
                onUiEvent(
                    formEditObject.copy(schoolName = schoolName)
                )
            },
            label = "School Name",
            placeholder = "School Name",
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (schoolName.isEmpty()) .5f else 1f),
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (uiInfo != null) {
            SubmitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = "Add Student",
                isEnabled = uiInfo.isAddStudentButtonEnabled,
                onClick = {
                    onUiEvent(
                        AddStudentContract.Event.AddStudent(
                            firstName,
                            lastName,
                            mobileNumber,
                            whatsappNumber,
                            schoolName,
                            studentClass
                        )
                    )
                }
            )
        }
    }

}