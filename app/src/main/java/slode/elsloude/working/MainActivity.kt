package slode.elsloude.working

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import slode.elsloude.working.pojo.Links
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    val url = URL("https://raw.githubusercontent.com/elslode/ForWork/main/config.json")
    lateinit var list: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thread = Thread {
            loadURL(url)
            Log.d("myRes", loadURL(url).toString())
            val linksArray = loadURL(url).getJSONObject("links").toString()
            Log.d("myRes", linksArray)
            val lastsElement: String = loadURL(url).getJSONObject("links").getString("2")
            Log.d("myRes", lastsElement)
        }
        thread.start()
    }

    fun loadURL(urls: URL): JSONObject {
        var result: JSONObject? = null
        var connection: HttpURLConnection? = null
        connection = url.openConnection() as HttpURLConnection?
        val inputStream = connection?.getInputStream()
        val inputStreamReader = InputStreamReader(inputStream)
        val reader = BufferedReader(inputStreamReader)
        val builder = StringBuilder()
        var line = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }
        result = JSONObject(builder.toString())
        return result
    }
}