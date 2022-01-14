<template>
  <div class="admin">
    Bienvenue dans la partie administation de la Bank
    Création d’un compte!
    <form>
      <p>
      <span> lastName :</span>
      <input name ="lastname" v-model="lastName" />
      </p>
      <p>
      <span> firstName :</span>
      <input name ="firstname" v-model="firstName" />
      </p>
      <p>
      <span> email :</span>
      <input name ="email" v-model="email" />
      </p>
      <p>
      <span> tel :</span>
      <input name ="tel" v-model="tel" />
      </p>
      <p>
      <span> balance fond du compte  :</span>
      <input name ="balance" v-model="balance" />
      </p>
      <p>
        <span> overdraft découvert possible  :</span>
        <input name ="overdraft" v-model="overdraft" />
      </p>
      <p>
        <span> cap combient il a le droit de retirée d’un coup :</span>
        <input name ="cap" v-model="cap" />
      </p>
      <a href= "#" v-on:click="formSubmit"></a>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Home',
  data : function(){
    return{
      lastName : "",
      firstName:"",
      email : "",
      tel : "",
      balance: "",
      overdraft : "",
      cap : ""
    }
  },
  methods: {
    formSubmit: function(){
      this.creatAccount()
    },
    creatAccount(){
      const url = this.BASE_URL+"/admin/add/"+this.lastName+"/"+this.firstName+"/"+this.email+"/"+this.tel+"/"+this.balance+"/"+this.overdraft+"/"+this.cap
      axios.post(url).then(account =>
          this.addNewClient(account.lastName, account.firstName, account.email,account.tel,account.balance, account.overdraft,account.cap)
      );
    },

    addNewClient(lastName,firstName, email,tel,balance, overdraft,cap) {
      this.lastName = lastName;
      this.firstName = firstName;
      this.email = email;
      this.tel = tel;
      this.balance = balance;
      this.overdraft = overdraft;
      this.cap = cap;
    }
  }
}
</script>

<style scoped>

</style>