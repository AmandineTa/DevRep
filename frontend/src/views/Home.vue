<template>
  <div class="home">
    <ul>
      <li>
        <img id ="logo" alt="su logo" src="../assets/su.png">
      </li>
    </ul>

    <HelloWorld msg="DevRep project "/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
//import json from "@/assets/default.json"

export default {
  name: 'Home',
  /*
  data:function (){
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
  width: 100%;

}
</style>