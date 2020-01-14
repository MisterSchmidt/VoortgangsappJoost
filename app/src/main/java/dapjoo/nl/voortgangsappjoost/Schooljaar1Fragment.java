package dapjoo.nl.voortgangsappjoost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Schooljaar1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schooljaar1, container, false);

        String[] schooljaar1Items = {"hoi", "hoi", "hallo", "poepje", "1","hoi", "hoi", "hallo", "poepje", "1","hoi", "hoi", "hallo", "poepje", "1","hoi", "hoi", "hallo", "poepje", "1"};

        ListView listView = (ListView) view.findViewById(R.id.schooljaar1ListView);

        //Array Adapter
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                schooljaar1Items
        );

        listView.setAdapter(listViewAdapter);

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
}
