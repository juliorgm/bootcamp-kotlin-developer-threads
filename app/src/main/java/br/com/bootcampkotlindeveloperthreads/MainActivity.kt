package br.com.bootcampkotlindeveloperthreads

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.bootcampkotlindeveloperthreads.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLoad.setOnClickListener {
            launchAstrosTask()
        }
    }

    fun showData(list: List<AstroPeople>?){
        binding.textName.text=""
        list?.forEach{ people ->
            binding.textName.append("${people.name} - ${people.craft} \n\n")
        }
    }

    fun showLoadingIndicator(){
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoadingIndicator(){
        binding.progressBar.visibility = View.GONE
    }


    fun launchAstrosTask(){
        val task = TaskAstros()
        task.execute()
    }

    inner class TaskAstros : AsyncTask<Void, Int, List<AstroPeople>>(){
        val repository = AstrosRepository()
        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstroPeople> {
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstroPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    }
}