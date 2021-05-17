<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Add doctor" : "Edit doctor" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="doctor.name" label="Name" />
          <v-text-field v-model="patient.username" label="username" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteDoctor">Delete doctor</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "DoctorDialog",
  props: {
    doctor: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.doctors
          .create({
            name: this.doctor.name,
            username: this.doctor.username,
            consultations: this.doctor.cnp,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.doctors
          .edit({
            id: this.doctor.id,
            name: this.doctor.name,
            username: this.doctor.username,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletePatient() {
      api.doctors
        .delete({
          id: this.doctor.id,
        })
        .then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.doctor.id;
    },
  },
};
</script>

<style scoped></style>
