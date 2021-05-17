import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allConsultations() {
    return HTTP.get(BASE_URL + "/secretaries/consultations", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  myConsultations() {
    return HTTP.get(BASE_URL + "/doctors/consultations/${consultation.doctor.id}", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(consultation) {
    return HTTP.post(BASE_URL + "/secretaries/consultations", consultation, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  edit(consultation) {
    return HTTP.patch(BASE_URL + "/secretaries/consultations/${consultation.id}", consultation, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  doctorEdit(consultation) {
    return HTTP.patch(BASE_URL + "/doctors/consultations/${consultation.doctor}", consultation, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  delete(consultation) {
    return HTTP.delete(BASE_URL + "/secretaries/consultations/${consultation.id}", consultation, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
