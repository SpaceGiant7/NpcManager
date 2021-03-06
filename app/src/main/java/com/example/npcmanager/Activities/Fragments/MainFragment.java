package com.example.npcmanager.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.AddPersonActivity;
import com.example.npcmanager.Activities.AddQuestActivity;
import com.example.npcmanager.Activities.FindByTraitActivity;
import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapters.BaseItemRecyclerAdapter;
import com.example.npcmanager.Activities.ViewQuestActivity;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private BaseItemRecyclerAdapter adapter;
    private TextView campaignName;
    private ImageButton backButton;
    private ImageButton searchButton;
    private ImageButton questButton;
    private ImageButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.fragmentMainQuestList);
        campaignName = view.findViewById(R.id.fragmentMainTitle);
        backButton = view.findViewById(R.id.fragmentMainBackButon);
        searchButton = view.findViewById(R.id.fragmentMainSearchButton);
        questButton = view.findViewById(R.id.fragmentMainQuestButton);
        addButton = view.findViewById(R.id.fragmentMainAddButton);

        setupRecyclerView();
        setupButtons();
        setCampaignName();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseItemRecyclerAdapter(
                () -> ApplicationModels.getQuestModel().getAllItems(),
                item -> selectQuest(item.getIdentifier()),
                item -> deleteQuest(item.getIdentifier()),
                getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void selectQuest(String quest) {
        ActivityUtilities.loadActivityWithExtra(
                getActivity(),
                ViewQuestActivity.class,
                ApplicationModels.getQuestModel().getItemMaybe(quest).get(),
                ActivityUtilities.questKey);
    }

    private void deleteQuest(String quest) {
        ApplicationModelUpdater.removeQuest(quest, getContext());
        adapter.reloadItems();
    }

    private void setupButtons() {
        backButton.setOnClickListener(
                v -> ActivityUtilities.loadCampaignSelectorActivity(getActivity()));
        searchButton.setOnClickListener(
                v -> ActivityUtilities.loadActivity(getActivity(), FindByTraitActivity.class));
        questButton.setOnClickListener(
                v -> ActivityUtilities.loadActivity(getActivity(), AddQuestActivity.class));
        addButton.setOnClickListener(
                v -> ActivityUtilities.loadActivity(getActivity(), AddPersonActivity.class));
    }

    private void setCampaignName() {
        campaignName.setText(ApplicationModels.getCampaignName());
    }
}
