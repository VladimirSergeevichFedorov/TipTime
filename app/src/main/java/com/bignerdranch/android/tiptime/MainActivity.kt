package com.bignerdranch.android.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding//код инициализирует переменную перед ее использованием

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//инициализирует binding объект, который вы будете использовать для доступа Views в activity_main.xml макете.
        setContentView(binding.root)//устанавливает доступ ко всем Views
        binding.calculateButton.setOnClickListener { calculateTip() }// слушатель(ссылаеться через binding на кнопку calculateButton в activity) с присвоенной функцией
    }
    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()//присваевам полю текст который получаем от юзера
        val cost = stringInTextField.toDoubleOrNull() //преобразуем в дабл
        if (cost == null) {//проверка на нул
            binding.tipResult.text = ""//передаёт пустую строку если цена не указана
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId//получаем процент чаевых который выбрал пользователь
        val tipPercentage = when(selectedId){//юзер выбрал процент, какой нам сообщает selectedId, в соответсвии с id мы компилят. присваивает переменной значение %
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost//расчёт чаевых исходя из выбранного процента

        if (binding.roundUpSwitch.isChecked) {//если юзер нажмёт округлить, то выполняеться округление
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)//формат чаевых в долларах
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)//упаковываем в геттер что получит пользователь после расчёта

    }
}
//
//