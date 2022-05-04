<template>
  <!-- Modal -->
  <div class="modal fade" id="newFilter" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
       aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Add New Filter</h5>
          <button @click="hideValidatorMessage" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form>
            <!-- Insert filter name -->
            <div class="row">
              <div class="form-outline mb-3 d-flex align-items-center gap-1">
                <label class="form-label" for="enterName"><b>Filter name</b></label>
                <input placeholder="My Filter 1" type="text" id="enterName" class="form-control w-25"
                       v-model="filterName"/>
              </div>
            </div>

            <!-- Insert filter criteria(s) -->
            <div class="row">
              <ul v-for="(criteria, key) in criterias" class="m-0" :key="criteria.id">
                <div class="form-outline mb-2 d-flex align-items-center gap-1">
                  <label class="form-label text-center" for="enterType"><b v-if="key === '1'">Criteria</b></label>
                  <select id="enterType" class="form-select w-25" v-model="criteria.selectedType"
                          v-on:change="defaultCondition(key)">
                    <option :value="'amount'" selected>Amount</option>
                    <option :value="'title'">Title</option>
                    <option :value="'date'">Date</option>
                  </select>
                  <select class="form-select w-25" v-model="criteria.selectedCondition">
                    <option :value="condition" v-for="condition in conditions[criteria.selectedType].condition"
                            :key="condition.id">
                      {{ condition }}
                    </option>
                  </select>
                  <div class="w-25">
                    <input v-if="criteria.selectedType === 'amount'" type="number" :id="'val' + key"
                           class="form-control" v-model="criteria.insertedValue"/>
                    <input v-else-if="criteria.selectedType === 'title'" type="text" :id="'val' + key"
                           class="form-control" v-model="criteria.insertedValue"/>
                    <input v-else-if="criteria.selectedType === 'date'" type="date" :id="'val' + key"
                           class="form-control" v-model="criteria.insertedValue"/>
                  </div>
                  <button v-if="key !== '1'" type="button" class="btn btn-danger btn-sm" style="width: 2rem"
                          @click="removeCriteria(key)"><span style="font-size: large"><b>-</b></span></button>
                </div>
              </ul>
              <div class="d-flex mb-3 justify-content-center">
                <button type="button" class="btn btn-secondary btn-sm" @click="addNewCriteria">+ ADD ROW</button>
              </div>
            </div>

            <!-- Select one -->
            <div class="row">
              <div class="form-outline mb-2 d-flex align-items-center gap-1">
                <label class="form-label me-2"><b>Selection</b></label>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1"
                         checked>
                  <label class="form-check-label" for="inlineRadio1">Select 1</label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2">
                  <label class="form-check-label" for="inlineRadio2">Select 2</label>
                </div>
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="3">
                  <label class="form-check-label" for="inlineRadio3">Select 3</label>
                </div>
              </div>
            </div>

            <p style="color: green; display: none" id="valid">Filter Created Successfully</p>
            <p style="color: darkred; display: none" id="invalid">Something went wrong</p>

          </form>
        </div>
        <div class="modal-footer">
          <button @click="hideValidatorMessage" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="addFilter">Sumbit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery';
import axios from "axios";

export default {
  name: "FilterModal",
  data() {
    return {
      filterName: "",
      selection: null,
      conditions: {
        'amount': {
          condition: ["More than", "Less than", "Equals"],
          type: "number",
        },
        'title': {
          condition: ["Starts with", "Ends with", "Contains"],
          type: "text"
        },
        'date': {
          condition: ["From", "To", "Is"],
          type: "date"
        },
      },
      criterias: {
        1: {
          selectedType: "amount",
          selectedCondition: "More than",
          insertedValue: null,
        }
      },
    }
  },
  methods: {
    defaultCondition(key) {
      this.criterias[key].insertedValue = null;
      this.criterias[key].selectedCondition = this.conditions[this.criterias[key].selectedType].condition[0]
    },
    removeCriteria(key) {
      delete this.criterias[key];
    },
    addNewCriteria() {
      this.criterias[parseInt(Object.keys(this.criterias)[Object.keys(this.criterias).length - 1]) + 1] = {
        selectedType: "amount",
        selectedCondition: "More than",
        insertedValue: null,
      }
    },
    resetFilter() {
      Object.assign(this.$data, this.$options.data());
    },
    hideValidatorMessage() {
      $('#valid').css("display", "none");
      $('#invalid').css("display", "none");
    },
    async addFilter() {
      const valid = $('#valid');
      const invalid = $('#invalid');
      this.hideValidatorMessage();
      if (this.filterName.length === 0) {
        invalid.css("display", "block");
      } else {
        for (const index in this.criterias) {
          const criteria = this.criterias[index];
          // Empty input value check
          if (criteria.insertedValue === null || criteria.insertedValue === "") {
            invalid.css("display", "block");
            throw new Error("Empty input value");
          }
        }
        // Post new filter to database
        let generatedKey = -1;
        await axios.post('http://localhost:8080/filter/new', {
          "name": this.filterName,
          "selection": $('input[name=inlineRadioOptions]:checked').val()
        }).then((response) => {
          generatedKey = response.data;
          if (generatedKey === -1) throw new Error("Something weng wrong");
        });
        for (const index in this.criterias) {
          const criteria = this.criterias[index];
          const type = criteria.selectedType;
          await axios.post(`http://localhost:8080/${type}/new`, {
            "condition": criteria.selectedCondition,
            [type]: criteria.insertedValue,
            "filter_id": generatedKey,
          })
        }
        this.$emit("addFilter");
        this.resetFilter();
        valid.css("display", "block");
      }
    }
  }
}
</script>

<style scoped>
.form-label {
  display: flex;
  width: 13%;
}

.modal-content {
  max-height: 75vh;
  overflow-y: auto;
}
</style>