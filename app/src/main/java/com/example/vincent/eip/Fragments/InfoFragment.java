package com.example.vincent.eip.Fragments;

/**
 * Created by iNfecteD on 23/02/2017.
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.vincent.eip.Activity.LoginActivity;
        import com.example.vincent.eip.Activity.MainActivity;
        import com.example.vincent.eip.GlobalClass;
        import com.example.vincent.eip.Interfaces.InfosCallback;
        import com.example.vincent.eip.Network.SendData;
        import com.example.vincent.eip.Network.UserClientInfo;
        import com.example.vincent.eip.R;

        import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;


public class InfoFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    TextView test;

    private Activity mContainerActivity;
    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View fragView = inflater.inflate(R.layout.fragment_info, container, false);


        mContainerActivity = (Activity) getContext();
        test = (TextView) fragView.findViewById(R.id.test);

//        SendData data = new SendData();
//         GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
//        UserClientInfo clientInfo = global.userInfos;
//        data.getOpeningHours(mContainerActivity, clientInfo, new InfosCallback(){
//            @Override
//            public void OnRequestDone(boolean isFailed, String result) {
//                if (isFailed){
//                    test.setText(result);
//                } else {
//                    test.setText("Error when loading data");
//                }
//            }
//        });


//        final GlobalClass global=(GlobalClass) mContainerActivity.getApplication();
//        mRecyclerView = (RecyclerView) fragView.findViewById(R.id.my_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(mContainerActivity);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        // specify an adapter (see also next example)
//        mAdapter = new SectorsMainPageAdapter(global.listSectors, new OnSectorClick() {
//            @Override
//            public void OnSectorClick(final String idSector) {
//                data.getServicesFromSector(mContainerActivity, global.userInfos, idSector, new OnTaskCompleted() {
//                    @Override
//                    public void onTaskCompleted() {
//                        Intent myIntent = new Intent(mContainerActivity, ServiceActivity.class);
//                        myIntent.putExtra("sector", idSector); //Optional parameters
//                        mContainerActivity.startActivity(myIntent);
//                    }
//                });
//
//            }
//        });
//        mRecyclerView.setAdapter(mAdapter);
//        data.getAllSectors(mContainerActivity, global.userInfos, new OnTaskCompleted() {
//            @Override
//            public void onTaskCompleted() {
//                mAdapter = new SectorsMainPageAdapter(global.listSectors, new OnSectorClick() {
//                    @Override
//                    public void OnSectorClick(final String idSector) {
//                        data.getServicesFromSector(mContainerActivity, global.userInfos, idSector, new OnTaskCompleted() {
//                            @Override
//                            public void onTaskCompleted() {
//                                Intent myIntent = new Intent(mContainerActivity, ServiceActivity.class);
//                                myIntent.putExtra("sector", idSector); //Optional parameters
//
//                                mContainerActivity.startActivity(myIntent);
//                            }
//                        });
//                    }
//                });
//                mAdapter.notifyDataSetChanged();
//            }
//        });
        return fragView;
    }

}