<template>
  <v-card>
    <v-card-title>
      Secretary: Consultations
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="consultations"
      :search="search"
      @click:row="editConsultations"
    ></v-data-table>
    <ConsultationDialog
      :opened="dialogVisible"
      :consultation="selectedConsultation"
      @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ConsultationDialog from "../components/ConsultationDialog";

export default {
  name: "Doctor",
  components: { ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Doctor", value: "doctor" },
        { text: "Patient", value: "patient" },
        { date: "Time", value: "time" },
        { text: "Details", value: "details" },
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.consultations = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
