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

public class KeuzevakkenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keuzevakken, container, false);

        String[] keuzevakkenItems = {"hoi", "hoi", "hallo", "poepje"};

        ListView listView = (ListView) view.findViewById(R.id.keuzevakkenListView);

        //Array Adapter
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                keuzevakkenItems
        );

        listView.setAdapter(listViewAdapter);

        return view;
    }
}
