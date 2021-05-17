<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Add patient" : "Edit patient" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.time" label="Time" />
          <v-text-field v-model="consultation.details" label="Details" />
          <v-text-field v-model="consultation.patient" label="Patient" />
          <v-text-field v-model="consultation.doctor" label="Doctor" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteConsultation">Delete consultation</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
          .create({
            time: this.consultation.time,
            details: this.consultation.details,
            patient: this.consultation.patient,
            doctor: this.consultation.doctor,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.consultations
          .edit({
            id: this.consultation.id,
            time: this.consultation.time,
            details: this.consultation.details,
            patient: this.consultation.patient,
            doctor: this.consultation.doctor,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation() {
      api.patients
        .delete({
          id: this.consultation.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
