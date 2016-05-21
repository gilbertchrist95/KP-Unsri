package kpunsri.telkomsel.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.adapter.AdapterMitra;

public class FormMitra extends AppCompatActivity {

    private RecyclerView recyclerViewTab3;
    public SearchView searchView;
    private List<String> list = new ArrayList<String>();
    AdapterMitra mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaringan_mitra_telkomsel);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView)findViewById(R.id.search);
        recyclerViewTab3 = (RecyclerView)findViewById(R.id.recyclerViewTab3);
        createList();

        recyclerViewTab3.setHasFixedSize(true);
        recyclerViewTab3.setLayoutManager(new LinearLayoutManager(this));
        // call the adapter with argument list of items and context.
        mAdapter = new AdapterMitra(list,this);
        recyclerViewTab3.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(listener);
    }

    private void createList() {
        list.add("Alang–Alang Lebar");
        list.add("Bandar Jaya");
        list.add("Basuki Rahmat");
        list.add("Baturaja");
        list.add("Belitang");
        list.add("Bengkulu Indah Mall");
        list.add("Betung");
        list.add("Curup");
        list.add("Indralaya");
        list.add("Jambi Inner");
        list.add("Kalianda");
        list.add("Kayu Agung");
        list.add("Kedaton");
        list.add("Kenten");
        list.add("Kotabumi");
        list.add("Kuala Tungkai");
        list.add("Manna");
        list.add("MDP");
        list.add("Merangin");
        list.add("Metro");
        list.add("Muara Enim");
        list.add("Muntok");
        list.add("Natar");
        list.add("Palembang Square");
        list.add("Prabumulih");
        list.add("Pringsewu");
        list.add("PS");
        list.add("Raden Inten");
        list.add("Sarolangun");
        list.add("Sebrang Ulu");
        list.add("Sekayu");
        list.add("Sribawono");
        list.add("Sungai Liat");
        list.add("Sungai Penuh");
        list.add("Teluk Betung");
        list.add("Tulang Bawang");
    }

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            query = query.toLowerCase();
            final List<String> filteredList = new ArrayList<>();

            for (int i = 0; i<list.size();i++){
                final String text = list.get(i).toLowerCase();
                if (text.contains(query)){
                    filteredList.add(list.get(i));
                }
            }

            recyclerViewTab3.setLayoutManager(new LinearLayoutManager(FormMitra.this));
            mAdapter = new AdapterMitra(filteredList,FormMitra.this);
            recyclerViewTab3.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id  = item.getItemId();

        if (id  == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
