package com.headoncloud.mylittleprojects.calculatorprovip

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.headoncloud.mylittleprojects.calculatorprovip.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import org.w3c.dom.Text
import java.lang.StringBuilder

//private fun String.insert(index: Int, msg: String): String = substring(0, index+1) + msg +substring(index+1)

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private lateinit var strResult: String
    private var strInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)
        initValue()
        initOnclick()
    }

    private fun EditText.hideKeyboard() {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        if (currentFocus != null) {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
//        }
//        return super.dispatchTouchEvent(ev)
//    }

    private fun initValue() {
        mActivityMainBinding.btn0.tag = 0
        mActivityMainBinding.btn1.tag = 1
        mActivityMainBinding.btn2.tag = 2
        mActivityMainBinding.btn3.tag = 3
        mActivityMainBinding.btn4.tag = 4
        mActivityMainBinding.btn5.tag = 5
        mActivityMainBinding.btn6.tag = 6
        mActivityMainBinding.btn7.tag = 7
        mActivityMainBinding.btn8.tag = 8
        mActivityMainBinding.btn9.tag = 9

        mActivityMainBinding.btnPlus.tag = "+"
        mActivityMainBinding.btnMinus.tag = "-"
        mActivityMainBinding.btnTimes.tag = "*"
        mActivityMainBinding.btnPercent.tag = "%"
        mActivityMainBinding.btnDivide.tag = "/"

        mActivityMainBinding.edtInput.background = null
    }
    


    private fun calculate(express: String){
        val expressionStr = express
        val expression = ExpressionBuilder(expressionStr).build()
        strResult = expression.evaluate().toString()
        mActivityMainBinding.tvResult.text = strResult
    }
    

    private fun initOnclick(){
        mActivityMainBinding.btn0.setOnClickListener(this)
        mActivityMainBinding.btn1.setOnClickListener(this)
        mActivityMainBinding.btn2.setOnClickListener(this)
        mActivityMainBinding.btn3.setOnClickListener(this)
        mActivityMainBinding.btn4.setOnClickListener(this)
        mActivityMainBinding.btn5.setOnClickListener(this)
        mActivityMainBinding.btn6.setOnClickListener(this)
        mActivityMainBinding.btn7.setOnClickListener(this)
        mActivityMainBinding.btn8.setOnClickListener(this)
        mActivityMainBinding.btn9.setOnClickListener(this)

        mActivityMainBinding.btnPlus.setOnClickListener(this)
        mActivityMainBinding.btnMinus.setOnClickListener(this)
        mActivityMainBinding.btnTimes.setOnClickListener(this)
        mActivityMainBinding.btnDivide.setOnClickListener(this)
        mActivityMainBinding.btnPercent.setOnClickListener(this)

        mActivityMainBinding.btnDot.setOnClickListener(this)

        mActivityMainBinding.btnAC.setOnClickListener(this)
        mActivityMainBinding.btnDelete.setOnClickListener(this)
        mActivityMainBinding.btnEqual.setOnClickListener(this)

        mActivityMainBinding.edtInput.setOnClickListener(this)
        mActivityMainBinding.edtInput.hideKeyboard()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn0,
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8,
                R.id.btn9 ->{
                    Log.d("Button Click", "button = ${v.tag.toString()}")
                    inputNumber(v.tag.toString())
                }

            R.id.btnEqual -> {
                calculate(strInput)
            }

            R.id.btnPlus,
                R.id.btnMinus,
                R.id.btnTimes,
                R.id.btnDivide,
                R.id.btnPercent -> {
                Log.d("Button Click", "button = ${v.tag.toString()}")
                inputNumber(v.tag.toString())
                }

            R.id.btnAC -> {
                mActivityMainBinding.edtInput.setText("")
                strInput = ""
            }

            R.id.btnDelete -> {
                deleteAnEle()
            }

            R.id.btnEqual -> {
                calculate(strInput)
            }
        }
    }

    private fun deleteAnEle() {
        //lay vi tri hien tai cua con tro
        val cursorPosition = mActivityMainBinding.edtInput.selectionStart
        //xoa phan tu o vi tri phia truoc contro
        if(cursorPosition != 0){
            strInput = strInput.removeRange(cursorPosition - 1, cursorPosition)
            //hien thi lai du lieu
            mActivityMainBinding.edtInput.setText(strInput)
            //cap nhat lai vi tri con tro
            mActivityMainBinding.edtInput.setSelection(cursorPosition-1)
        }

    }

    private fun inputNumber(number: String){
        //Copy chuoi dau vao
        strInput = mActivityMainBinding.edtInput.text.toString()
        Log.d("Input Number", "strInput = ${mActivityMainBinding.edtInput.text.toString()}")
        //Khoi tao StringBuilder
        val strBuilder: StringBuilder = StringBuilder(strInput)
        //Lay vi tri cua con tro
        val cursorPosition = mActivityMainBinding.edtInput.selectionStart
        Log.d("Input Number", "cursorPosition = ${mActivityMainBinding.edtInput.selectionStart}")
        //Lay so lieu muon chen
        val inputNum = number
        Log.d("Input Number", "inputNum = $number")
        //chen so lieu vao chuoi voi vi tri chi dinh
        strInput = strBuilder.insert(cursorPosition, inputNum).toString()
        Log.d("Input Number", "strInput after insert = ${strInput}")
        //cap nhat lai thay doi
        mActivityMainBinding.edtInput.setText(strInput)
        Log.d("Input Number", "edtInput = ${mActivityMainBinding.edtInput.setText(strInput)}")
        //Di chuyen con tro chuot toi vi tri sau khi chen ky tu
        mActivityMainBinding.edtInput.setSelection(cursorPosition+1)
        Log.d("Input Number", "cursorPosition after = ${mActivityMainBinding.edtInput.setSelection(cursorPosition+1)}")
    }

    /*private fun inputCalculate(operator: String){
        //Copy chuoi dau vao
        strInput = mActivityMainBinding.edtInput.text.toString()
        Log.d("Input Number", "strInput = ${mActivityMainBinding.edtInput.text.toString()}")
        //Cap nhat chuoi
        strInput += operator

        when(operator){
            "+" -> {

            }
        }
    }*/







}

/*
* Hàm insert() được lấy từ thư viện StringBuilder
* */