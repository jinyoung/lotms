<template>
    <div>
    <h1>업체</h1>
        <v-row>
            <v-card
                class="mx-auto"
                style="height:300px; width:300px; margin-bottom:20px; text-align: center;"
                outlined
            >
                <v-list-item>
                    <v-list-item-avatar 
                        class="mx-auto"
                        size="80"
                        style="margin-top:80px;"
                    ><v-icon color="primary" x-large>mdi-plus</v-icon>
                    </v-list-item-avatar>
                </v-list-item>

                <v-card-actions>
                    <v-btn 
                        v-on="on"
                        class="mx-auto"
                        outlined
                        rounded
                        @click="openDialog=true;"
                        color="primary"
                        style="font-weight:500; font-size:20px; padding:15px; border:solid 2px; max-width:250px; overflow:hidden"
                    >
                        업체 등록
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-row>
        <v-list two-line>
            <template>
                <v-list-item v-for="(data, n) in values" :key="n">
                    <v-list-item-avatar color="grey darken-1">
                        <v-img :src="data.photo ? data.photo:'https://cdn.vuetifyjs.com/images/cards/cooking.png'"/>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title style="margin-bottom:10px;">
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                        </v-list-item-title>

                        <v-list-item-subtitle style="font-size:25px; font-weight:700;">
                            [ CompanyType :  {{data.companyType }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ CompanyName :  {{data.companyName }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ CompanyCode :  {{data.companyCode }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ RegistrationNumber :  {{data.registrationNumber }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ BusinessType :  {{data.businessType }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ Industry :  {{data.industry }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ CeoName :  {{data.ceoName }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ Phone :  {{data.phone }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ FaxNumber :  {{data.faxNumber }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ Email :  {{data.email }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ MarginRate :  {{data.marginRate }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ AccountNumber :  {{data.accountNumber }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ BankName :  {{data.bankName }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ Beneficiary :  {{data.beneficiary }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ PaymentTerms :  {{data.paymentTerms }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ IsActive :  {{data.isActive }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ SalesRepresentative :  {{data.salesRepresentative }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ CompanyIssues :  {{data.companyIssues }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ Address :  {{data.address }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </v-list-item-subtitle>

                    </v-list-item-content>
                </v-list-item>

                <v-divider v-if="n !== 6" :key="`divider-${n}`" inset></v-divider>
            </template>
        </v-list>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >

                    <BasicCompany :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import BasicCompany from './../BasicCompany.vue';

    export default {
        name: 'BasicCompanyManager',
        components: {
            BasicCompany,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            } 

            var temp = await axios.get(axios.fixUrl('/companies'))
            temp.data._embedded.companies.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.companies;
            
            this.newValue = {
                'companyType': '',
                'companyName': '',
                'companyCode': '',
                'registrationNumber': '',
                'businessType': '',
                'industry': '',
                'ceoName': '',
                'phone': '',
                'faxNumber': '',
                'email': {},
                'marginRate': 0,
                'accountNumber': '',
                'bankName': '',
                'beneficiary': '',
                'paymentTerms': '',
                'isActive': false,
                'salesRepresentative': '',
                'companyIssues': '',
                'address': {},
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            }
        },
    };
</script>


<style>
    .video-card {
        width:300px; 
        margin-left:4.5%; 
        margin-top:50px; 
        margin-bottom:50px;
    }
</style>

