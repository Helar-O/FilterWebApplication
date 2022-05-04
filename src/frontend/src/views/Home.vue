<template>
  <filter-modal @addFilter="addFilter"/>
  <div style="background-color: darkcyan; min-height: 100vh;" class="p-3">
    <div class="container p-4 border rounded">
      <!-- Button trigger modal -->
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newFilter">
        New Filter
      </button>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Name</th>
          <th scope="col">Selection</th>
          <th scope="col">Type</th>
          <th scope="col">Condition</th>
          <th scope="col">Value</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(filter, index) in filters" :key="filter.id">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ filter.first.name }}</td>
          <td>Select {{ filter.first.selection }}</td>
          <td v-for="nr in 3" :key="nr.id">
            <span style="text-transform: capitalize" class="d-flex flex-column my-1" v-for="(criteria) in filter.second" :key="criteria.id">
              {{ nr === 1 ? Object.keys(criteria)[2]  : nr === 3 ? Object.values(criteria)[nr - 1] : Object.values(criteria)[nr - 1] }}
            </span>
          </td>
          <td style="width: 0">
            <button type="button" class="btn btn-danger btn-sm"
                    @click="removeFilter(filter.first.filter_id, index)">Remove</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import FilterModal from "@/components/FilterModal";
import axios from "axios";

export default {
  name: "HomePage",
  components: {FilterModal},
  data() {
    return {
      filters: null,
    }
  },
  beforeMount() {
    axios.get('http://localhost:8080/filter/all')
        .then((response) => this.filters = response.data);
  },
  methods: {
    addFilter() {
      axios.get('http://localhost:8080/filter/all')
          .then((response) => this.filters = response.data);
    },
    removeFilter(filter_id, index) {
      axios.post(`http://localhost:8080/filter/remove/${filter_id}`);
      this.filters.splice(index, 1);
    }
  }
}
</script>

<style scoped>
  @media only screen and (max-width: 600px) {
    * {
      font-size: 0.7rem;
    }
    td {
      padding-right: 0;
    }
  }
</style>