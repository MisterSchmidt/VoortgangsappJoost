package dapjoo.nl.voortgangsappjoost;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import dapjoo.nl.voortgangsappjoost.model.Vak;

import static android.content.Context.MODE_PRIVATE;

public class Schooljaar1Fragment extends Fragment {

    ArrayList<Vak> vakken;
    String[] schooljaar1Items;

    protected void onCreate(){
        loadDataToFragment();
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schooljaar1, container, false);

        //TEST MET ITERATOR
        /*Iterator itr = vakken.iterator();
        while(itr.hasNext()){
            Vak vak = (Vak)itr.next();

        }*/

        String[] schooljaar1Items = {vakken.get(2).getNaam()};

        //Array Adapter

        ListView listView = (ListView) view.findViewById(R.id.schooljaar1ListView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                schooljaar1Items
        );

        listView.setAdapter(listViewAdapter);

        //OnClickListener voor arraylist
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Toast.makeText(getActivity(), "Yay", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;


    }

    public void loadDataToFragment(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vakData", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("objectJson", null);
        Type type = new TypeToken<ArrayList<Vak>>() {}.getType();
        vakken = gson.fromJson(json, type);
    }
}
