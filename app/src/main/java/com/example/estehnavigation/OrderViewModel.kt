package com.example.estehnavigation

import androidx.lifecycle.ViewModel
import com.example.estehnavigation.Data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat


    private const val HARGA_PER_CUP = 8000

    class OrderViewModel : ViewModel() {
        private val _stateUI = MutableStateFlow(OrderUiState())
        val  stateUI: StateFlow<OrderUiState> = _stateUI.asStateFlow()

        fun setCustomersDetails(nama : String, nomor: String, alamat: String){
            _stateUI .update { stateSaatIni ->
                stateSaatIni.copy(
                    namaPelanggan = nama,
                    nomorTelepon = nomor,
                    alamat = alamat
                )
            }
        }

        fun setJumlah(jmlEsTeh:Int) {
            _stateUI.update { stateSaatIni ->
                stateSaatIni.copy(
                    jumlah = jmlEsTeh,
                    harga = hitungharga(jumlah = jmlEsTeh)
                )
            }
        }
        fun setRasa(rasaPilihan: String) {
            _stateUI.update { stateSaatIni ->
                stateSaatIni.copy(rasa = rasaPilihan)
            }
        }

        fun resetOrder(){
            _stateUI.value = OrderUiState()
        }

        private fun hitungharga(
            jumlah: Int = _stateUI.value.jumlah,
        ): String {
            val kalkulasiharga = jumlah * HARGA_PER_CUP

            return NumberFormat.getNumberInstance().format(kalkulasiharga)
        }
        fun setContact(listData:MutableList<String>){
            _stateUI.value = OrderUiState(
                namaPelanggan = listData[0],
                alamat = listData[1],
                nomorTelepon = listData[2],
            )
        }

    }
