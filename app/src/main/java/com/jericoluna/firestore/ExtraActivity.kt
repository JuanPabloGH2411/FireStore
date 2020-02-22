package com.jericoluna.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_extra.*

class ExtraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra)

        println("Micurp : GAHJ931124H0CRRN07 es valida :${"GAHJ931124H0CRRN07".isCurpValidate()}")

        println("Micurp : GAHJ931124H0CRRN es valida :${"GAHJ931124H0CRRN".isCurpValidate()}")

        ivExtension.loadUrl("https://www.gameartguppy.com/wp-content/uploads/2019/04/mascot_android-jetpack-plans.png")


        val nombres = arrayListOf("Ana","Carlos","Daniel","Pablo")
        println("nombre :${nombres[nombres.penultimoItem]}")

        inflateSpinner()
        showListView()
    }


    override fun onResume() {
        super.onResume()
        if(isNetworkAvailable()){
            Toast.makeText(this,"Red Disponible",Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(this,"Red no Disponible",Toast.LENGTH_SHORT).show()

        }
    }

    fun inflateSpinner(){
        val postres = arrayListOf("Cupcake","Donut","Eclair","Froyo")
        val arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,postres)
        spinner.adapter=arrayAdapter
        spinner.onItemSelectedListener =object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {

                Toast.makeText(baseContext,postres[position],Toast.LENGTH_LONG).show()
            }

        }
    }


    fun showListView(){
        val estados =resources.getStringArray(R.array.list_states)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,estados)
        lvStates.adapter =adapter
        lvStates.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                Toast.makeText(baseContext,estados[position],Toast.LENGTH_SHORT).show()
            }

        }
    }


    //ListViewCustom

}
