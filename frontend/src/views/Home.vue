<template>
  <div class="home">
    <ul>
      <li >
        <img id ="logo" alt="Vue logo" src="src/assets/logoBank/default.png" />
      </li>
      <li>
        <p>
          <strong>Account n°<span id="accountId"></span></strong> <br />
          <small>Current balance : <span id="accountBalance"></span>
            <span id="accountCurrency"></span></small>
        </p>
      </li>
    </ul>

    <HelloWorld msg="Default description" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
//import json from "@/assets/default.json"

export default {
  name: 'Home',
  /*data:function (){
    return{
      banks: json["1"],
    };
  },*/
  components: {
    HelloWorld
  }
}
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}

#logo{
  width: 10%;
}
</style>