<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="primary"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="primary-darker-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                CompanyType :  {{item.companyType }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CompanyName :  {{item.companyName }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CompanyCode :  {{item.companyCode }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                RegistrationNumber :  {{item.registrationNumber }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                BusinessType :  {{item.businessType }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Industry :  {{item.industry }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CeoName :  {{item.ceoName }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Phone :  {{item.phone }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                FaxNumber :  {{item.faxNumber }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Email :  {{item.email }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                MarginRate :  {{item.marginRate }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Address :  {{item.address }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                AddressDetails :  {{item.addressDetails }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                AccountNumber :  {{item.accountNumber }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                BankName :  {{item.bankName }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                Beneficiary :  {{item.beneficiary }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                PaymentTerms :  {{item.paymentTerms }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                IsActive :  {{item.isActive }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                PrimaryDeliveryLocation :  {{item.primaryDeliveryLocation }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                SecondaryDeliveryLocation :  {{item.secondaryDeliveryLocation }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                SalesRepresentative :  {{item.salesRepresentative }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                CompanyIssues :  {{item.companyIssues }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="primary-darker-1"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'BasicCompanyPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/companies'))
            if(temp.data) {
                me.list = temp.data._embedded.companies;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/companies/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    me.list.forEach(function(item, idx) {
                        if(item.name == val.name) {
                            me.selected = idx
                        }
                    })
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    
                    
                    
                    
                    obj['companyCode'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

