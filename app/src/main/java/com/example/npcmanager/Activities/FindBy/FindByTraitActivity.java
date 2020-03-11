package com.example.npcmanager.Activities.FindBy;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.RecyclerAdapters.BaseItemRecyclerAdapter;
import com.example.npcmanager.Activities.Utilities.UserInterfaceUtilities;
import com.example.npcmanager.Activities.ViewPersonActivity;
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

    private TextView enableRaceText;
    private TextView enableGenderText;
    private TextView enableLocationText;
    private TextView enableOccupationText;
    private TextView enableOrganizationText;
    private TextView enableMortalityText;

    private Spinner raceSelector;
    private Spinner genderSelector;
    private Spinner locationSelector;
    private Spinner occupationSelector;
    private Spinner organizationSelector;
    private Spinner mortalitySelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_trait);
        recyclerView = findViewById(R.id.findByTraitRecyclerVier);

        enableRaceText = findViewById(R.id.findByTraitRaceLabel);
        enableGenderText = findViewById(R.id.findByTraitGenderLabel);
        enableLocationText = findViewById(R.id.findByTraitLocationLabel);
        enableOccupationText = findViewById(R.id.findByTraitOccupationLabel);
        enableOrganizationText = findViewById(R.id.findByTraitOrganizationLabel);
        enableMortalityText = findViewById(R.id.findByTraitMortalityLabel);

        raceSelector = findViewById(R.id.findByTraitRaceSelector);
        genderSelector = findViewById(R.id.findByTraitGenderSelector);
        locationSelector = findViewById(R.id.findByTraitLocationSelector);
        occupationSelector = findViewById(R.id.findByTraitOccupationSelector);
        organizationSelector = findViewById(R.id.findByTraitOrganizationSelector);
        mortalitySelector = findViewById(R.id.findByTraitMortalitySelector);

        setupRecyclerView();
        setupSelectors();
        setupEnablers();
        setupDisablers();
        disableAll();
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

    private void setupSelectors() {
        setupRaceSelector();
        setupGenderSelector();
        setupLocationSelector();
        setupOccupationSelector();
        setupOrganizationSelector();
        setupMortalitySelector();
    }

    private void setupRaceSelector() {
        UserInterfaceUtilities.setupSelector(
                raceSelector,
                ApplicationModels.getRaceModel().getList(),
                this,
                race -> updateAndReload(() -> personFilterer.addRaceFilter(race)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.RACE)));
    }

    private void setupGenderSelector() {
        UserInterfaceUtilities.setupSelector(
                genderSelector,
                ApplicationModels.getGenderModel().getList(),
                this,
                gender -> updateAndReload(() -> personFilterer.addGenderFilter(gender)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.GENDER)));
    }

    private void setupLocationSelector() {
        UserInterfaceUtilities.setupSelector(
                locationSelector,
                ApplicationModels.getLocationModel().getList(),
                this,
                location -> updateAndReload(() -> personFilterer.addLocationFilter(location)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.LOCATION)));
    }

    private void setupOccupationSelector() {
        UserInterfaceUtilities.setupSelector(
                occupationSelector,
                ApplicationModels.getOccupationModel().getList(),
                this,
                occupation -> updateAndReload(() -> personFilterer.addOccupationFilter(occupation)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.OCCUPATION)));
    }

    private void setupOrganizationSelector() {
        UserInterfaceUtilities.setupSelector(
                organizationSelector,
                ApplicationModels.getOrganizationModel().getList(),
                this,
                organization -> updateAndReload(() -> personFilterer.addOrganizationFilter(organization)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.ORGANIZATION)));
    }

    private void setupMortalitySelector() {
        UserInterfaceUtilities.setupSelector(
                mortalitySelector,
                Mortality.getList(),
                this,
                mortality -> updateAndReload(() -> personFilterer.addMortalityFilter(mortality)),
                () -> updateAndReload(() -> personFilterer.removeFilter(PersonTrait.MORTALITY)));
    }

    void updateAndReload(Runnable runnable) {
        runnable.run();
        adapter.reloadItems();
    }

    private void setupEnablers() {
        enableRaceText.setOnClickListener(v -> enableRace());
        enableGenderText.setOnClickListener(v -> enableGender());
        enableLocationText.setOnClickListener(v -> enableLocation());
        enableOccupationText.setOnClickListener(v -> enableOccupation());
        enableOrganizationText.setOnClickListener(v -> enableOrganization());
        enableMortalityText.setOnClickListener(v -> enableMortality());
    }

    private void setupDisablers() {
        raceSelector.setOnLongClickListener(v -> disableRace());
        genderSelector.setOnLongClickListener(v -> disableGender());
        locationSelector.setOnLongClickListener(v -> disableLocation());
        occupationSelector.setOnLongClickListener(v -> disableOccupation());
        organizationSelector.setOnLongClickListener(v -> disableOrganization());
        mortalitySelector.setOnLongClickListener(v -> disableMortality());
    }

    private void disableAll() {
        disableRace();
        disableGender();
        disableLocation();
        disableOccupation();
        disableOrganization();
        disableMortality();
    }

    private void enableRace() {
        enableItem(enableRaceText, raceSelector,
                () -> personFilterer.addRaceFilter(
                        (Race) raceSelector.getSelectedItem()));
    }

    private void enableGender() {
        enableItem(enableGenderText, genderSelector,
                () -> personFilterer.addGenderFilter(
                        (Gender) genderSelector.getSelectedItem()));
    }

    private void enableLocation() {
        enableItem(enableLocationText, locationSelector,
                () -> personFilterer.addLocationFilter(
                        (Location) locationSelector.getSelectedItem()));
    }

    private void enableOccupation() {
        enableItem(enableOccupationText, occupationSelector,
                () -> personFilterer.addOccupationFilter(
                        (Occupation) occupationSelector.getSelectedItem()));

    }

    private void enableOrganization() {
        enableItem(enableOrganizationText, organizationSelector,
                () -> personFilterer.addOrganizationFilter(
                        (Organization) organizationSelector.getSelectedItem()));
    }

    private void enableMortality() {
        enableItem(enableMortalityText, mortalitySelector,
                () -> personFilterer.addMortalityFilter(
                        (Mortality) mortalitySelector.getSelectedItem()));
    }

    private boolean disableRace() {
        return disableItem(enableRaceText, raceSelector, PersonTrait.RACE);
    }

    private boolean disableGender() {
        return disableItem(enableGenderText, genderSelector, PersonTrait.GENDER);
    }

    private boolean disableLocation() {
        return disableItem(enableLocationText, locationSelector, PersonTrait.LOCATION);
    }

    private boolean disableOccupation() {
        return disableItem(enableOccupationText, occupationSelector, PersonTrait.OCCUPATION);
    }

    private boolean disableOrganization() {
        return disableItem(enableOrganizationText, organizationSelector, PersonTrait.ORGANIZATION);
    }

    private boolean disableMortality() {
        return disableItem(enableMortalityText, mortalitySelector, PersonTrait.MORTALITY);
    }

    private void selectPerson(String name) {
        ActivityUtilities.loadActivityWithPersonExtra(
                this, ViewPersonActivity.class, name);
    }

    private void deletePerson(String name) {
        ApplicationModelUpdater.removePerson(name, this);
        adapter.reloadItems();
    }

    private void enableItem(View enableText, View itemSelector, Runnable runnable) {
        enableText.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private boolean disableItem(View enableText, Spinner itemSelector, PersonTrait trait) {
        enableText.setVisibility(View.VISIBLE);
        itemSelector.setVisibility(View.INVISIBLE);
        itemSelector.setSelection(0);
        personFilterer.removeFilter(trait);
        adapter.reloadItems();
        return true;
    }
}
