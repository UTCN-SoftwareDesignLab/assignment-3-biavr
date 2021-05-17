<template>
  <v-card>
    <v-card-title>
      Secretary: Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="patients"
      :search="search"
      @click:row="editPatient"
    ></v-data-table>
    <PatientDialog
      :opened="dialogVisible"
      :patient="selectedPatient"
      @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";

export default {
  name: "PatientList",
  components: { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Name", value: "name" },
        { text: "Id card", value: "isCardNo" },
        { text: "CNP", value: "cnp" },
        { text: "Birth date", value: "birthDate" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
