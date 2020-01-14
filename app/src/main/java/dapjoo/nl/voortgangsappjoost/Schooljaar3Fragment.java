package dapjoo.nl.voortgangsappjoost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Schooljaar3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schooljaar3, container, false);

        String[] schooljaar3Items = {"hoi", "hoi", "hallo", "poepje"};

        ListView listView = (ListView) view.findViewById(R.id.schooljaar3ListView);

        //Array Adapter
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                schooljaar3Items
        );

        listView.setAdapter(listViewAdapter);

        return view;
    }
}
