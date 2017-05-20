package com.pickpic.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.pickpic.Activity.GalleryActivity;
import com.pickpic.Activity.SearchActivity;
import com.pickpic.Adapter.TimeTabGridViewAdaptor;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTabFragment extends Fragment {

    private View view;

    public TimeTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.time_tab_fragment, container, false);
        TimeTabGridViewAdaptor adaptor = new TimeTabGridViewAdaptor();
        final GridView gridView = (GridView) view.findViewById(R.id.time_tab_gridview);

        inflater.inflate(R.layout.time_tab_fragment, container, false);
        gridView.setAdapter(adaptor);

        LocalImageManager.getTimeTabGridViewItemList(getContext(),adaptor);

        final ArrayList<String> imagepath = LocalImageManager.getAllImagePath(getContext());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                String selectedimage = imagepath.get(position);
                //Toast.makeText(getContext(), "GridView clicked " + position + "\n" + selectedimage, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("filepath", selectedimage);
                startActivity(intent);
            }
        });
        return view;
    }

}
