const STORAGE_KEY = 'todos-vuejs'

export default {

  fetch: function(key) {
    return window.JSON.parse(window.localStorage.getItem(key) || '[]')
  },

  save: function(key,items) {
    window.localStorage.setItem(key, window.JSON.stringify(items))
  },
  clear: function () {
    let token = this.fetch('token');
    window.localStorage.clear();
    this.save("token",token);
  },

  fetchSession: function (key) {
    return window.JSON.parse(window.sessionStorage.getItem(key) || '[]')
  },

  saveSession: function (key, items) {
    window.sessionStorage.setItem(key,window.JSON.stringify(items));
  }
}
