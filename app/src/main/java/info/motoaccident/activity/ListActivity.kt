package info.motoaccident.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.jakewharton.rxbinding.view.RxView
import info.motoaccident.R
import info.motoaccident.decorators.ListDecorator
import info.motoaccident.dictionaries.DEVELOPER
import info.motoaccident.dictionaries.MODERATOR
import info.motoaccident.dictionaries.PHONE
import info.motoaccident.dictionaries.STANDARD
import info.motoaccident.utils.bindView
import rx.Observable
import rx.Subscription

class ListActivity : AppCompatActivity(), ListActivityInterface {
    private val createAccidentButton by bindView<ImageButton>(R.id.create_acc)
    private val callButton by bindView<ImageButton>(R.id.call)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)

    lateinit private var callButtonSubscription: Subscription
    lateinit private var createAccidentButtonSubscription: Subscription

    override fun getPermittedResources(): Observable<Pair<View, Int>> = Observable.just(
            Pair(createAccidentButton, STANDARD or MODERATOR or DEVELOPER),
            Pair(callButton, PHONE))

    val listView by bindView<RecyclerView>(R.id.list_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar);
    }

    override fun onResume() {
        super.onResume()
        ListDecorator.start(this)
        callButtonSubscription = RxView.clicks(callButton).subscribe { callPressed() }
        createAccidentButtonSubscription = RxView.clicks(callButton).subscribe { createPressed() }
    }

    override fun onPause() {
        super.onPause()
        ListDecorator.stop()
        callButtonSubscription.unsubscribe()
        createAccidentButtonSubscription.unsubscribe()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_map     -> runActivity(MapActivity::class.java)
            R.id.action_settings -> runActivity(SettingsActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

    fun callPressed() {
        //TODO implementation
    }

    fun createPressed() {
        //TODO implementation
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.list_activity_toolbar, menu);
        return true;
    }

    override fun runActivity(activity: Class<*>, bundle: Bundle) {
        startActivity(Intent(this, activity).putExtras(bundle))
    }

    override fun getContext(): Context = this

    override fun contentView(): RecyclerView = listView
}
