package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.covid_app.R

class ShopFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shops, container, false)
        view.findViewById<TextView>(R.id.textView_test).text = "Shops is here"


        return view
    }


    companion object {
        fun newInstance(): ShopFragment = ShopFragment()
    }

}