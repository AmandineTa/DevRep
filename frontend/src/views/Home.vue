<template>
  <div class="home">

        <img id ="logo" alt="Vue logo" src="../assets/su.png" />

    <HelloWorld msg="Présentation du projet de DevRep" />
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


#logo{
  width: 50%;
}
</style>