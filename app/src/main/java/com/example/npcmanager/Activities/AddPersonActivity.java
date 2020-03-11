package com.example.npcmanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.Activities.Utilities.UserInterfaceUtilities;
import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Mortality;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModelUpdater;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.R;

import java.util.Optional;
import java.util.function.Supplier;

public class AddPersonActivity extends HomeButtonActivity {

    TextView enableRaceText;
    TextView enableGenderText;
    TextView enableHomeText;
    TextView enableOccupationText;
    TextView enableOrganizationText;
    TextView enableMortalityText;
    TextView enableDescriptionText;
    TextView raceLabel;
    TextView genderLabel;
    TextView homeLabel;
    TextView occupationLabel;
    TextView organizationLabel;
    TextView mortalityLabel;
    TextView descriptionLabel;
    private Optional<Person> existingPerson;
    private TextView nameTextInput;
    private Spinner raceSelector;
    private Spinner genderSelector;
    private Spinner homeSelector;
    private Spinner occupationSelector;
    private Spinner organizationSelector;
    private Spinner mortalitySelector;
    private TextView descriptionTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        nameTextInput = findViewById(R.id.addPersonNameTextInput);
        raceSelector = findViewById(R.id.addPersonRaceSelector);
        genderSelector = findViewById(R.id.addPersonGenderSelector);
        homeSelector = findViewById(R.id.addPersonHomeSelector);
        occupationSelector = findViewById(R.id.addPersonOccupationSelector);
        organizationSelector = findViewById(R.id.addPersonOrganizationSelector);
        mortalitySelector = findViewById(R.id.addPersonMortalitySelector);
        descriptionTextInput = findViewById(R.id.addPersonDescriptionTextInput);

        enableRaceText = findViewById(R.id.addPersonEnableRaceText);
        enableGenderText = findViewById(R.id.addPersonEnableGenderText);
        enableHomeText = findViewById(R.id.addPersonEnableHomeText);
        enableOccupationText = findViewById(R.id.addPersonEnableOccupationText);
        enableOrganizationText = findViewById(R.id.addPersonEnableOrganizationText);
        enableMortalityText = findViewById(R.id.addPersonEnableMortalityText);
        enableDescriptionText = findViewById(R.id.addPersonEnableDescriptionText);

        raceLabel = findViewById(R.id.addPersonRaceTextField);
        genderLabel = findViewById(R.id.addPersonGenderTextField);
        homeLabel = findViewById(R.id.addPersonHomeTextField);
        occupationLabel = findViewById(R.id.addPersonOccupationTextField);
        organizationLabel = findViewById(R.id.addPersonOrganizationTextField);
        mortalityLabel = findViewById(R.id.addPersonMortalityTextField);
        descriptionLabel = findViewById(R.id.addPersonDescriptionTextField);

        Button saveButton = findViewById(R.id.addPersonSaveButton);
        Button deleteButton = findViewById(R.id.addPersonDeleteButton);

        existingPerson = ActivityUtilities.getPersonExtraMaybe(getIntent());
        populateInputs();

        saveButton.setOnClickListener(v -> saveButtonClicked());
        deleteButton.setOnClickListener(v -> deleteButtonClicked());

        setupEnablers();
        setupDisablers();
    }

    private void populateInputs() {
        if (existingPerson.isPresent()) {
            populateInputs(existingPerson.get());
        } else {
            populateInputs(Person.None());
        }
    }

    private void populateInputs(Person person) {
        determineEnabledState(person.getRace(), this::disableRace, this::enableRace);
        determineEnabledState(person.getGender(), this::disableGender, this::enableGender);
        determineEnabledState(person.getHome(), this::disableHome, this::enableHome);
        determineEnabledState(person.getOccpation(), this::disableOccupation, this::enableOccupation);
        determineEnabledState(person.getOrganization(), this::disableOrganization, this::enableOrganization);
        determineEnabledState(person.getMortality(), this::disableMortality, this::enableMortality);
        if (person.isNone()) {
            nameTextInput.setText("");
            disableDescription();
        } else {
            nameTextInput.setText(person.getName());
            enableDescription();
        }
        setupSelectors(person);
        descriptionTextInput.setText(person.getDescription());
    }

    private void setupSelectors(Person person) {
        setupRaceSelector(person.getRace());
        setupGenderSelector(person.getGender());
        setupHomeSelector(person.getHome());
        setupOccupationSelector(person.getOccpation());
        setupOrganizationSelector(person.getOrganization());
        setupMortalitySelector(person.getMortality());
    }

    private void determineEnabledState(BaseItem item, Runnable disable, Runnable enable) {
        if (item.isNone()) {
            disable.run();
        } else {
            enable.run();
        }
    }

    private void setupHomeSelector(Location home) {
        UserInterfaceUtilities.setupSelector(
                homeSelector, ApplicationModels.getLocationModel().getList(), home, this);
    }

    private void setupOccupationSelector(Occupation occupation) {
        UserInterfaceUtilities.setupSelector(occupationSelector,
                ApplicationModels.getOccupationModel().getList(), occupation, this);
    }

    private void setupOrganizationSelector(Organization organization) {
        UserInterfaceUtilities.setupSelector(organizationSelector,
                ApplicationModels.getOrganizationModel().getList(), organization, this);
    }

    private void setupMortalitySelector(Mortality mortality) {
        UserInterfaceUtilities.setupSelector(
                mortalitySelector, Mortality.getList(), mortality, this);
    }

    private void setupGenderSelector(Gender gender) {
        UserInterfaceUtilities.setupSelector(
                genderSelector, ApplicationModels.getGenderModel().getList(), gender, this);
    }

    private void setupRaceSelector(Race race) {
        UserInterfaceUtilities.setupSelector(
                raceSelector, ApplicationModels.getRaceModel().getList(), race, this);
    }

    private void setupEnablers() {
        enableRaceText.setOnClickListener(v -> enableRace());
        enableGenderText.setOnClickListener(v -> enableGender());
        enableHomeText.setOnClickListener(v -> enableHome());
        enableOccupationText.setOnClickListener(v -> enableOccupation());
        enableOrganizationText.setOnClickListener(v -> enableOrganization());
        enableMortalityText.setOnClickListener(v -> enableMortality());
        enableDescriptionText.setOnClickListener(v -> enableDescription());
    }

    private void setupDisablers() {
        raceLabel.setOnLongClickListener(v -> disableRace());
        raceSelector.setOnLongClickListener(v -> disableRace());

        genderLabel.setOnLongClickListener(v -> disableGender());
        genderSelector.setOnLongClickListener(v -> disableGender());

        homeLabel.setOnLongClickListener(v -> disableHome());
        homeSelector.setOnLongClickListener(v -> disableHome());

        occupationLabel.setOnLongClickListener(v -> disableOccupation());
        occupationSelector.setOnLongClickListener(v -> disableOccupation());

        organizationLabel.setOnLongClickListener(v -> disableOrganization());
        organizationSelector.setOnLongClickListener(v -> disableOrganization());

        mortalityLabel.setOnLongClickListener(v -> disableMortality());
        mortalitySelector.setOnLongClickListener(v -> disableMortality());

        descriptionLabel.setOnLongClickListener(v -> disableDescription());
        descriptionTextInput.setOnLongClickListener(v -> disableDescription());
    }

    private void createPerson() {
        Person newPerson = new Person(
                nameTextInput.getText().toString(),
                getSelectedRace(),
                getSelectedGender(),
                getSelectedLocation(),
                getSelectedOccupation(),
                getSelectedOrganization(),
                getSelectedMortality(),
                descriptionTextInput.getText().toString());

        if (existingPerson.isPresent()) {
            ApplicationModelUpdater.replacePerson(existingPerson.get().getName(), newPerson, this);
        } else {
            ApplicationModelUpdater.addPerson(newPerson, this);
        }
    }

    private Race getSelectedRace() {
        return (Race) getSelectedItem(enableRaceText, raceSelector, Race::None);
    }

    private Gender getSelectedGender() {
        return (Gender) getSelectedItem(enableGenderText, genderSelector, Gender::None);
    }

    private Location getSelectedLocation() {
        return (Location) getSelectedItem(enableHomeText, homeSelector, Location::None);
    }

    private Occupation getSelectedOccupation() {
        return (Occupation) getSelectedItem(
                enableOccupationText, occupationSelector, Occupation::None);
    }

    private Organization getSelectedOrganization() {
        return (Organization) getSelectedItem(
                enableOrganizationText, organizationSelector, Organization::None);
    }

    private Mortality getSelectedMortality() {
        return (Mortality) getSelectedItem(enableMortalityText, mortalitySelector, Mortality::None);
    }

    private BaseItem getSelectedItem(
            View enableText, Spinner selector, Supplier<BaseItem> noneSupplier) {
        if (enableText.getVisibility() == View.INVISIBLE) {
            return UserInterfaceUtilities.getDeselectableSelection(selector, noneSupplier);
        } else {
            return noneSupplier.get();
        }
    }
    
    private void saveButtonClicked() {
        createPerson();
        ActivityUtilities.loadMainActivity(this);
    }

    private void deleteButtonClicked() {
        existingPerson.ifPresent(
                person -> ApplicationModelUpdater.removePerson(person.getName(), this));
        ActivityUtilities.loadMainActivity(this);
    }

    private void enableRace() {
        enableItem(enableRaceText, raceLabel, raceSelector);
    }

    private void enableGender() {
        enableItem(enableGenderText, genderLabel, genderSelector);
    }

    private void enableHome() {
        enableItem(enableHomeText, homeLabel, homeSelector);
    }

    private void enableOccupation() {
        enableItem(enableOccupationText, occupationLabel, occupationSelector);
    }

    private void enableOrganization() {
        enableItem(enableOrganizationText, organizationLabel, organizationSelector);
    }

    private void enableMortality() {
        enableItem(enableMortalityText, mortalityLabel, mortalitySelector);
    }

    private void enableDescription() {
        enableItem(enableDescriptionText, descriptionLabel, descriptionTextInput);
    }

    private boolean disableRace() {
        return disableItem(enableRaceText, raceLabel, raceSelector,
                () -> setupRaceSelector(Race.None()));
    }

    private boolean disableGender() {
        return disableItem(enableGenderText, genderLabel, genderSelector,
                () -> setupGenderSelector(Gender.None()));
    }

    private boolean disableHome() {
        return disableItem(enableHomeText, homeLabel, homeSelector,
                () -> setupHomeSelector(Location.None()));
    }

    private boolean disableOccupation() {
        return disableItem(enableOccupationText, occupationLabel, occupationSelector,
                () -> setupOccupationSelector(Occupation.None()));
    }

    private boolean disableOrganization() {
        return disableItem(enableOrganizationText, organizationLabel, organizationSelector,
                () -> setupOrganizationSelector(Organization.None()));
    }

    private boolean disableMortality() {
        return disableItem(enableMortalityText, mortalityLabel, mortalitySelector,
                () -> setupMortalitySelector(Mortality.None()));
    }

    private boolean disableDescription() {
        return disableItem(enableDescriptionText, descriptionLabel, descriptionTextInput,
                () -> descriptionTextInput.setText(""));
    }

    private void enableItem(View enableText, View itemLabel, View itemSelector) {
        enableText.setVisibility(View.INVISIBLE);
        itemLabel.setVisibility(View.VISIBLE);
        itemSelector.setVisibility(View.VISIBLE);
    }

    private boolean disableItem(View enableText, View itemLabel, View itemSelector, Runnable runnable) {
        enableText.setVisibility(View.VISIBLE);
        itemLabel.setVisibility(View.INVISIBLE);
        itemSelector.setVisibility(View.INVISIBLE);
        runnable.run();
        return true;
    }
}
