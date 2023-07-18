<template>
    <v-card outlined>
        <v-card-title>
            Company # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="CompanyType" v-model="item.companyType" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CompanyName" v-model="item.companyName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CompanyCode" v-model="item.companyCode" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RegistrationNumber" v-model="item.registrationNumber" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BusinessType" v-model="item.businessType" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Industry" v-model="item.industry" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CeoName" v-model="item.ceoName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Phone" v-model="item.phone" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="FaxNumber" v-model="item.faxNumber" :editMode="editMode" @change="change" />
            </div>
            <Email offline label="Email" v-model="item.email" :editMode="false" @change="change" />
            <div>
                <Number label="MarginRate" v-model="item.marginRate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="AccountNumber" v-model="item.accountNumber" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="BankName" v-model="item.bankName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Beneficiary" v-model="item.beneficiary" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PaymentTerms" v-model="item.paymentTerms" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Boolean label="IsActive" v-model="item.isActive" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="SalesRepresentative" v-model="item.salesRepresentative" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CompanyIssues" v-model="item.companyIssues" :editMode="editMode" @change="change" />
            </div>
            <Address offline label="Address" v-model="item.address" :editMode="false" @change="change" />
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'BasicCompanyDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/companies/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
