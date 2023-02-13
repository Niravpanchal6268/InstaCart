package com.example.instacart.Auth.Customer.HomeF;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.Auth.Customer.HomeF.Categories.CategoriesAdpter;
import com.example.instacart.Auth.Customer.HomeF.Categories.CategoriesModel;
import com.example.instacart.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView categoriesRecyclerView;
    CategoriesAdpter categoriesAdpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoriesRecyclerView = view.findViewById(R.id.home_tops_product_recyclerView_id);

        //categories firebase to app
        FirebaseRecyclerOptions<CategoriesModel> options =
                new FirebaseRecyclerOptions.Builder<CategoriesModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories"), CategoriesModel.class)
                        .build();
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        categoriesAdpter = new CategoriesAdpter(options);
        categoriesRecyclerView.setAdapter(categoriesAdpter);

        //slider code offline
        





        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        categoriesAdpter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        categoriesAdpter.stopListening();

    }
}