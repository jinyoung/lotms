<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

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
            headers: 
                [
                    { text: "companyType", value: "companyType" },
                    { text: "companyName", value: "companyName" },
                    { text: "companyCode", value: "companyCode" },
                    { text: "registrationNumber", value: "registrationNumber" },
                    { text: "businessType", value: "businessType" },
                    { text: "industry", value: "industry" },
                    { text: "ceoName", value: "ceoName" },
                    { text: "phone", value: "phone" },
                    { text: "faxNumber", value: "faxNumber" },
                    { text: "email", value: "email" },
                    { text: "marginRate", value: "marginRate" },
                    { text: "address", value: "address" },
                    { text: "addressDetails", value: "addressDetails" },
                    { text: "accountNumber", value: "accountNumber" },
                    { text: "bankName", value: "bankName" },
                    { text: "beneficiary", value: "beneficiary" },
                    { text: "paymentTerms", value: "paymentTerms" },
                    { text: "isActive", value: "isActive" },
                    { text: "primaryDeliveryLocation", value: "primaryDeliveryLocation" },
                    { text: "secondaryDeliveryLocation", value: "secondaryDeliveryLocation" },
                    { text: "salesRepresentative", value: "salesRepresentative" },
                    { text: "companyIssues", value: "companyIssues" },
                ],
            company : [],
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
                'email': '',
                'marginRate': 0,
                'address': '',
                'addressDetails': '',
                'accountNumber': '',
                'bankName': '',
                'beneficiary': '',
                'paymentTerms': '',
                'isActive': false,
                'primaryDeliveryLocation': '',
                'secondaryDeliveryLocation': '',
                'salesRepresentative': '',
                'companyIssues': '',
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
            },
        }
    }
</script>

