<template>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bank Web Application</title>
    <link rel="apple-touch-icon" href="apple-touch-icon.png" />
    <!-- Place favicon.ico in the root directory -->
    <!--<link href="bulma.min.css" /> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css" />
    <!--link rel="stylesheet" href="styles/main.css" />-->

    <!-- Choose the icon between :
        - "./styles/banks/banque_postale.css"
        - "./styles/banks/cic.css"
        - "./styles/banks/societe_generale.css"

        ./styles/banks/banque_postale.css ./styles/banks/cic.css ./styles/banks/societe_generale.css
      <link rel="stylesheet" href="styles/banks/banque_postale.css" />
      -->

  </head>

  <body class="container" style="margin-top: 5%">
  <!-- Columns for login functions -->
  <div class="columns">
    <!-- Buttons -->
    <div class="column">
      <p v-if="islogged == false">
      <span id="login-buttons-container">

        <button class="button is-info is-outlined" id="google-login-button">
          <span class="icon">
            <i class="fab fa-google"></i>
          </span>
          <span>Google login</span>
        </button>
      </span>
      </p>
      <p if v-else>
      <span id="logout-button-container">
        <button class="button is-danger is-outlined" id="logout-button">
          <span class="icon">
            <i class="fas fa-sign-out-alt"></i>
          </span>
          <span>Logout</span>
        </button>
      </span>
      </p>

    </div>
    <!-- Column that contains log states tags -->
    <div class="column" style="text-align: right">
      <p v-if="islogged == false">
        <span id="not-logged-tag" class="tag is-warning is-medium">Not connected</span>
      </p>
      <p v-else>
        <span id="logged-tag" class="tag is-success is-medium">Connected</span>
      </p>
    </div>
  </div>

  <!-- The name and description -->
  <section class="section">
    <div class="container">
      <h1 class="title primary-color-text" id="bankNameContainer"></h1>
      <h2 class="subtitle secondary-color-text" id="bankDescriptionContainer"></h2>
    </div>
  </section>

  <div class="columns is-mobile">
    <!-- Account displaying -->
    <div class="column is-4 is-offset-2">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image is-64x64">
              <!-- Choose the icon between :
                  - "./images/bank_icons/banque_postale.png"
                  - "./images/bank_icons/cic.png"
                  - "./images/bank_icons/societe_generale.png"
                  ./images/bank_icons/banque_postale.png ./images/bank_icons/cic.png ./images/bank_icons/societe_generale.png
                -->
              <img id ="logo" alt="logo" src="../assets/logoBank/SG.png" />
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p v-if="islogged == true">
                <strong>Account n°<span id="accountId"></span></strong> <br />
                <small>Current balance : <span id="accountBalance"></span>
                  <span id="accountCurrency"></span></small>
              </p>
              <p v-else>
                <small>{{bankDesc}}</small>
              </p>
            </div>
            <nav class="level is-mobile">
              <div class="level-left">
                <a class="level-item" aria-label="info">
                  <span class="icon is-small primary-color-text">
                    <i class="fas fa-info-circle" aria-hidden="true"></i>
                  </span>
                </a>
              </div>
            </nav>
          </div>
        </article>
      </div>
    </div>
    <div class="column is-4 is-offset-1">
      <iframe title="Converter" src="https://xeconvert.com/widget1?from=usd&to=eur&lang=&theme=blue&font=12"
              width="100%" height="100%"></iframe>
    </div>
    <!-- Column that contains the Account box-->
  </div>

  <!-- The buttons -->
  <div class="columns">
    <div class="column" style="text-align: start">
      <button id="withdraw-button" onclick="withdraw()" class="button primary-color">
        <span class="icon">
          <i class="fas fa-arrow-down"></i>
        </span>
        <span>Withdraw</span>
      </button>
      <!--<label class="checkbox">
          <input id="limit-checkbox" type="checkbox" onchange="limitationChanged(this)">
          With limitation
        </label>-->
    </div>
    <div class="column" style="text-align: end">
      <button id="deposit-button" onclick="deposit()" class="button secondary-color">
        <span class="icon">
          <i><font-awesome-icon icon ="ArrowUp"/></i>
        </span>
        <span>Deposit</span>
      </button>
    </div>
  </div>

  <div class="columns is-mobile">
    <div class="column is-6">
      <div class="content secondary-color-text">
        Contact
        <ul>
          <li>
            <i><font-awesome-icon icon ="phone"/> {{bankphone}}</i>
            <span id="phoneNumberContainer"></span>
          </li>
          <li>
            <i><font-awesome-icon icon ="envelope"/> {{bankmail}}</i>
            <span id="mailContainer"></span>
          </li>
        </ul>
      </div>
    </div>

    <div class="column is-offset-4 is-2">
      <div class="columns is-mobile">
        <div class="column is-half">
          <figure class="image is-64x64">
            <img id ="logoM" alt="logoM" src="../assets/masterard.png" />
          </figure>
        </div>
        <div class="column is-half">
          <figure class="image is-64x64">
            <img id ="logoV" alt="logoV" src="../assets/visa.png" />
          </figure>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>

<script>
// @ is an alias to /src

import axios from 'axios';

export default {
  name: 'Home',
  withLimitation: false,
  components: {},
  // défini les variables de notre vue
  data: function () {
    return {
      islogged : false,
      BASE_URL : `https://localhost:8082/api/`,
      phone : '',
      mail : '',
      accountId : '',
      accountBalance : '0',
      overdraft : '0',
      bankName : "Société Générale",
      bankDesc : "Société Générale est une des principales banques françaises et une des plus anciennes. Elle fait partie des trois piliers de l'industrie bancaire française non mutualiste avec le Crédit lyonnais et BNP Paribas.",
      bankphone: "01 42 14 58 58",
      bankmail: "sg@sg.net"
    }
  },
  mounted : function () {
    this.initWebPageWithData();

    // this.getAccount();
  },
  methods: {

    deposit : function () {
      const defaultAmount = 200.0;
      const args = {
        accountId : this.accountId,
        amount: defaultAmount
      };
      const url = this.BASE_URL + "Deposit";
      console.log("axios post at : " + url)
      axios.post(url, args).then(account => {
        this.fillAccountData(account.id, account.balance, account.overdraft)
      })
    },

    withdraw : function () {
      if( confirm("Do you really want to withdraw {{amount}} € ?")) {
        console.log("Withdraw")
        const defaultAmount = 200;
        const args = {amount: defaultAmount};
        if (this.accountBalance - args.amount < this.overdraft) {
          // Not enough money on your account
          console.log("not Enough money TODO")
        }
        const url = this.BASE_URL + "Withdraw/";
        axios.get(url + this.accountId + "/" + args).then(account =>
            this.fillAccountData(account.id, account.balance, account.overdraft)
        );
      }
    },

    getAccount() {
      const url = this.BASE_URL;
      axios.get(url).then(account =>
          this.fillAccountData(account.id, account.balance, account.overdraft)
      );
    },

    fillAccountData(accountIdTemp, accountBalance, overdraft) {
      this.accountId = accountIdTemp;
      this.accountBalance = accountBalance;
      this.overdraft = overdraft;
    },

    limitationChanged(checkbox) {
      this.withLimitation = checkbox.checked;
    },

    initWebPageWithData() {
      let donnee = {
        "name": "La Banque Postale",
        "description": "La Banque postale est une banque publique française née le 1ᵉʳ janvier 2006, filiale à 100 % du groupe La Poste, dont elle a repris les services financiers. Son réseau de distribution s'appuie sur des bureaux de poste répartis sur le territoire, dont les agents opèrent au nom, et pour le compte de la banque.",
        "phoneNumber": "09 69 39 99 98",
        "mail": "laposte@laposte.net"
      }
      this.populateWebpageWithData( donnee.name, donnee.description, donnee.phoneNumber, donnee.mail);
    },
    populateWebpageWithData(
        bankName,
        bankDescription,
        phone,
        mail
    ) {
      this.bankName = bankName;
      this.bankDescription = bankDescription;
      this.phone = phone;
      this.mail = mail;
    }
  }
}
</script>

<style scoped>

.primary-color-text { color: #e60028; }
.secondary-color-text { color: #000000; }

.primary-color, .primary-color:disabled {
  background-color: #e60028;
  border-color: #000000;
  color: #000000;
}

.secondary-color, .secondary-color:disabled {
  background-color: #000000;
  border-color: #e60028;
  color: #e60028;;
}

#logo{
  width: 100%;
}
#logoM{
  width: 100%;
}
#logoV{
  width: 100%;
  margin-top: 13px;
}
</style>
