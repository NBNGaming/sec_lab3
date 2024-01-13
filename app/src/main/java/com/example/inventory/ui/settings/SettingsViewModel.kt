package com.example.inventory.ui.settings

import androidx.lifecycle.ViewModel
import com.example.inventory.data.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel: ViewModel() {
    fun initUiState() {
        _uiState.value = SettingsUiState(
            supplierName = Settings.defaultSupplierName,
            supplierEmail = Settings.defaultSupplierEmail,
            supplierNumber = Settings.defaultSupplierNumber,
            enableDefaultFields = Settings.enableDefaultFields,
            hideSensitiveData = Settings.hideSensitiveData,
            disableSharing = Settings.disableSharing,
        )
    }

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(supplierName = value)
    }

    fun onNumberChange(value: String) {
        _uiState.value = _uiState.value.copy(supplierNumber = value)
    }

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(supplierEmail = value)
    }

    fun onEnableDefaultFieldsChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(enableDefaultFields = value)
    }

    fun onHideSensitiveDataChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(hideSensitiveData = value)
    }

    fun onDisableSharingChange(value: Boolean) {
        _uiState.value = _uiState.value.copy(disableSharing = value)
    }

    fun save() {
        Settings.defaultSupplierName = uiState.value.supplierName
        Settings.defaultSupplierNumber = uiState.value.supplierNumber
        Settings.defaultSupplierEmail = uiState.value.supplierEmail
        Settings.enableDefaultFields = uiState.value.enableDefaultFields
        Settings.hideSensitiveData = uiState.value.hideSensitiveData
        Settings.disableSharing = uiState.value.disableSharing
    }
}

data class SettingsUiState(
    val supplierName: String = "",
    val supplierNumber: String = "",
    val supplierEmail: String = "",
    val enableDefaultFields: Boolean = false,
    val hideSensitiveData: Boolean = false,
    val disableSharing: Boolean = false,
)