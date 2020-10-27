package com.example.vsr1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vsr.PlaceHolderApi
import com.example.vsr.RetrofitClient
import retrofit2.Call
import kotlinx.android.synthetic.main.activity_fragment.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var items: MutableList<Joke> = mutableListOf(
        Joke("sdf", Value(listOf("sgsdg", "sdfsd"), 23, "fdsfd"))
    )
    val myAdapter = MainAdapter(items, object : MainAdapter.Callback{
        override fun onItemClicked(item: Joke) {
            TODO("Not yet implemented")
        }
    })
    lateinit var retrofit: PlaceHolderApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

            retrofit = RetrofitClient.instance()
        myRecycler.adapter = myAdapter
        findViewById<Button>(R.id.bigB).setOnClickListener {
            retrofit.getPost().enqueue(object : Callback<Joke> {
                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    Log.d("RETROFIT", t.message.toString())
                }

                override fun onResponse(
                    call: Call<Joke>,
                    response: Response<Joke>
                ) {
                    response.body()?.let { it1 -> items.add(it1) }
                    Log.d("RETROFIT", response.body()?.value?.joke.toString())
                }

            })
        }
    }
}
class MainAdapter(var items: List<Joke>, val callback: Callback) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val firstName = itemView.findViewById<TextView>(R.id.joketxt)

        fun bind(item: Joke) {
            firstName.text = item.value.joke.toString()
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Joke)
    }

}

