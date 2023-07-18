<template>
    <v-card style="width:450px; height:100%;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            업체 # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            업체
        </v-card-title >        

        <v-card-text>
            <String label="업체구분" v-model="value.companyType" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업체명" v-model="value.companyName" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업체코드" v-model="value.companyCode" :editMode="editMode" :inputUI="''"/>
            <String label="사업자등록번호" v-model="value.registrationNumber" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업태" v-model="value.businessType" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="종목" v-model="value.industry" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="대표자명" v-model="value.ceoName" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업체 전화번호" v-model="value.phone" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업체 팩스번호" v-model="value.faxNumber" :editMode="editMode" :inputUI="'TEXT'"/>
            <Email offline label="이메일주소" v-model="value.email" :editMode="editMode" @change="change"/>
            <Number label="마진율(%)" v-model="value.marginRate" :editMode="editMode" :inputUI="'TEXT'"/>
            <Address offline label="주소" v-model="value.address" :editMode="editMode" @change="change"/>
            <String label="주소상세" v-model="value.addressDetails" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="계좌번호" v-model="value.accountNumber" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="은행명" v-model="value.bankName" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="예금주" v-model="value.beneficiary" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="결제조건" v-model="value.paymentTerms" :editMode="editMode" :inputUI="'TEXT'"/>
            <Boolean label="사용여부" v-model="value.isActive" :editMode="editMode" :inputUI="'SELECT'"/>
            <Address offline label="주 납품처" v-model="value.address" :editMode="editMode" @change="change"/>
            <Address offline label="보조 납품처" v-model="value.address" :editMode="editMode" @change="change"/>
            <String label="영업 담당자" v-model="value.salesRepresentative" :editMode="editMode" :inputUI="'TEXT'"/>
            <String label="업체 이슈" v-model="value.companyIssues" :editMode="editMode" :inputUI="'TEXT'"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="edit"
                v-if="!editMode"
            >
                수정
            </v-btn>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                저장
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
                >
                    삭제
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    import Email from './vo/Email.vue';
    import Address from './vo/Address.vue';
    import Address from './vo/Address.vue';
    import Address from './vo/Address.vue';

    export default {
        name: 'BasicCompany',
        components:{
            Email,
            Address,
            Address,
            Address,
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: ''
            },
        }),
        computed:{
        },
        methods: {
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/companies'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
        },
    }
</script>

