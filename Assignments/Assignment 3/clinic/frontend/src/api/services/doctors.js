import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allDoctors() {
    return HTTP.get(BASE_URL + "/secretaries/doctors", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(doctor) {
    return HTTP.post(BASE_URL + "/secretaries/doctors", doctor, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  edit(doctor) {
    return HTTP.patch(BASE_URL + "/secretaries/doctors/${doctor.id}", doctor, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  delete(doctor) {
    return HTTP.delete(BASE_URL + "/secretaries/doctors/${patient.id}", doctor, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
