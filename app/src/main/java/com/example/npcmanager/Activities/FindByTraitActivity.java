package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.DeselectableSpinnerAdapter;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapters.BaseItemRecyclerAdapter;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Mortality;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.PersonTrait;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.PersonFilterer;
import com.example.npcmanager.R;

public class FindByTraitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BaseItemRecyclerAdapter adapter;
    private PersonFilterer personFilterer = new PersonFilterer();

    private TextView enableNameText;
    private TextView enableRaceText;
    private TextView enableGenderText;
    private TextView enableLocationText;
    private TextView enableOccupationText;
    private TextView enableOrganizationText;
    private TextView enableMortalityText;

    private EditText nameText;
    private DeselectableSpinnerAdapter raceSelector;
    private DeselectableSpinnerAdapter genderSelector;
    private DeselectableSpinnerAdapter locationSelector;
    private DeselectableSpinnerAdapter occupationSelector;
    private DeselectableSpinnerAdapter organizationSelector;
    private DeselectableSpinnerAdapter mortalitySelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_trait);
        recyclerView = findViewById(R.id.findByTraitRecyclerVier);

        enableNameText = findViewById(R.id.findByTraitNameLabel);
        enableRaceText = findViewById(R.id.findByTraitRaceLabel);
        enableGenderText = findViewById(R.id.findByTraitGenderLabel);
        enableLocationText = findViewById(R.id.findByTraitLocationLabel);
        enableOccupationText = findViewById(R.id.findByTraitOccupationLabel);
        enableOrganizationText = findViewById(R.id.findByTraitOrganizationLabel);
        enableMortalityText = findViewById(R.id.findByTraitMortalityLabel);

        nameText = findViewById(R.id.findByTraitNameText);

        setupRecyclerView();
        setupInputs();
        setupEnablers();
        setupDisablers();
        disableAll();
        checkForExtras();
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true); // Maybe not needed
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseItemRecyclerAdapter(
                () -> personFilterer.getPeople(),
                item -> selectPerson(item.getIdentifier()),
                item -> deletePerson(item.getIdentifier()),
                this);
        recyclerView.setAdapter(adapter);
    }

    private void setupInputs() {
        setupNameInput();
        setupRaceSelector();
        setupGenderSelector();
        setupLocationSelector();
        setupOccupationSelector();
        setupOrganizationSelector();
        setupMortalitySelector();
    }

    private void setupNameInput() {
        nameText.addTextChangedListener(new TextChangeWatcher());
    }

    private void setupRaceSelector() {
        raceSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitRaceSelector,
                ApplicationModels.getRaceModel().getList(),
                race -> updateAndReload(() -> personFilterer.addRaceFilter(race)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.RACE)),
                Race::None);
    }

    private void setupGenderSelector() {
        genderSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitGenderSelector,
                ApplicationModels.getGenderModel().getList(),
                gender -> updateAndReload(() -> personFilterer.addGenderFilter(gender)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.GENDER)),
                Gender::None);
    }

    private void setupLocationSelector() {
        locationSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitLocationSelector,
                ApplicationModels.getLocationModel().getList(),
                location -> updateAndReload(() -> personFilterer.addLocationFilter(location)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.LOCATION)),
                Location::None);
    }

    private void setupOccupationSelector() {
        occupationSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitOccupationSelector,
                ApplicationModels.getOccupationModel().getList(),
                occupation -> updateAndReload(() -> personFilterer.addOccupationFilter(occupation)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.OCCUPATION)),
                Occupation::None);
    }

    private void setupOrganizationSelector() {
        organizationSelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitOrganizationSelector,
                ApplicationModels.getOrganizationModel().getList(),
                organization -> updateAndReload(() -> personFilterer.addOrganizationFilter(organization)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.ORGANIZATION)),
                Organization::None);
    }

    private void setupMortalitySelector() {
        mortalitySelector = DeselectableSpinnerAdapter.create(
                this,
                R.id.findByTraitMortalitySelector,
                Mortality.getList(),
                mortality -> updateAndReload(() -> personFilterer.addMortalityFilter(mortality)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.MORTALITY)),
                Mortality::None);
    }

    void updateAndReload(Runnable runnable) {
        runnable.run();
        adapter.reloadItems();
    }

    private void setupEnablers() {
        enableNameText.setOnClickListener(v -> enableName());
        enableRaceText.setOnClickListener(v -> enableRace());
        enableGenderText.setOnClickListener(v -> enableGender());
        enableLocationText.setOnClickListener(v -> enableLocation());
        enableOccupationText.setOnClickListener(v -> enableOccupation());
        enableOrganizationText.setOnClickListener(v -> enableOrganization());
        enableMortalityText.setOnClickListener(v -> enableMortality());
    }

    private void setupDisablers() {
        nameText.setOnLongClickListener(v -> disableName());
        raceSelector.setOnLongClickListener(v -> disableRace());
        genderSelector.setOnLongClickListener(v -> disableGender());
        locationSelector.setOnLongClickListener(v -> disableLocation());
        occupationSelector.setOnLongClickListener(v -> disableOccupation());
        organizationSelector.setOnLongClickListener(v -> disableOrganization());
        mortalitySelector.setOnLongClickListener(v -> disableMortality());
    }

    private void disableAll() {
        disableName();
        disableRace();
        disableGender();
        disableLocation();
        disableOccupation();
        disableOrganization();
        disableMortality();
    }

    private void checkForExtras() {
        ActivityUtilities.getRaceExtraMaybe(getIntent())
                .ifPresent(race -> {
                    raceSelector.setSelection(race);
                    enableRace();
                });
        ActivityUtilities.getGenderExtraMaybe(getIntent())
                .ifPresent(gender -> {
                    genderSelector.setSelection(gender);
                    enableGender();
                });
        ActivityUtilities.getLocationExtraMaybe(getIntent())
                .ifPresent(location -> {
                    locationSelector.setSelection(location);
                    enableLocation();
                });
        ActivityUtilities.getOccupationExtraMaybe(getIntent())
                .ifPresent(occupation -> {
                    occupationSelector.setSelection(occupation);
                    enableOccupation();
                });
        ActivityUtilities.getOrganizationExtraMaybe(getIntent())
                .ifPresent(organization -> {
                    organizationSelector.setSelection(organization);
                    enableOrganization();
                });
        ActivityUtilities.getMortalityExtraMaybe(getIntent())
                .ifPresent(mortality -> {
                    mortalitySelector.setSelection(mortality);
                    enableMortality();
                });

    }

    private void enableName() {
        enableItem(enableNameText, nameText);
    }

    private void enableRace() {
        enableItem(enableRaceText, raceSelector);
    }

    private void enableGender() {
        enableItem(enableGenderText, genderSelector);
    }

    private void enableLocation() {
        enableItem(enableLocationText, locationSelector);
    }

    private void enableOccupation() {
        enableItem(enableOccupationText, occupationSelector);

    }

    private void enableOrganization() {
        enableItem(enableOrganizationText, organizationSelector);
    }

    private void enableMortality() {
        enableItem(enableMortalityText, mortalitySelector);
    }

    private boolean disableName() {
        nameText.setText("");
        return disableItem(enableNameText, nameText, PersonTrait.NAME);
    }

    private boolean disableRace() {
        return disableSpinner(enableRaceText, raceSelector, PersonTrait.RACE);
    }

    private boolean disableGender() {
        return disableSpinner(enableGenderText, genderSelector, PersonTrait.GENDER);
    }

    private boolean disableLocation() {
        return disableSpinner(enableLocationText, locationSelector, PersonTrait.LOCATION);
    }

    private boolean disableOccupation() {
        return disableSpinner(enableOccupationText, occupationSelector, PersonTrait.OCCUPATION);
    }

    private boolean disableOrganization() {
        return disableSpinner(enableOrganizationText, organizationSelector, PersonTrait.ORGANIZATION);
    }

    private boolean disableMortality() {
        return disableSpinner(enableMortalityText, mortalitySelector, PersonTrait.MORTALITY);
    }

    private void selectPerson(String name) {
        ActivityUtilities.loadActivityWithPersonExtra(
                this, ViewPersonActivity.class, name);
    }

    private void deletePerson(String name) {
        ApplicationModelUpdater.removePerson(name, this);
        adapter.reloadItems();
    }

    private void enableItem(View enableText, View itemSelector) {
        enableText.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private void enableItem(View enableText, DeselectableSpinnerAdapter itemSelector) {
        enableText.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private boolean disableSpinner(View enableText, DeselectableSpinnerAdapter itemSelector, PersonTrait trait) {
        itemSelector.setSelection(0);
        return disableItem(enableText, itemSelector, trait);
    }

    private boolean disableItem(View enableText, View input, PersonTrait trait) {
        enableText.setVisibility(View.VISIBLE);
        input.setVisibility(View.INVISIBLE);
        personFilterer.removeFilter(trait);
        adapter.reloadItems();
        return true;
    }

    private boolean disableItem(View enableText, DeselectableSpinnerAdapter input, PersonTrait trait) {
        enableText.setVisibility(View.VISIBLE);
        input.setVisibility(View.INVISIBLE);
        personFilterer.removeFilter(trait);
        adapter.reloadItems();
        return true;
    }

    private class TextChangeWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            personFilterer.addNameFilter(s.toString());
            adapter.reloadItems();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
}
