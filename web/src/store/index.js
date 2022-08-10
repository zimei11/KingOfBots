import { createStore } from 'vuex'
import ModuleUser from './user'
import ModulePK from './pk'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    pk:ModulePK,
  }
})
