# 

## Model
www.msaez.io/#/storming/25efccac1bec5ff4d8ea87ab1a45adb5

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- basic
- sales


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- basic
```
 http :8088/users userName="userName" id="id" password="password" employmentStatus="employmentStatus" position="position" department="department" phoneNumber="phoneNumber" joinDate="joinDate" isAdmin="isAdmin" isDeptManager="isDeptManager" 
 http :8088/companies companyType="companyType" companyName="companyName" companyCode="companyCode" registrationNumber="registrationNumber" businessType="businessType" industry="industry" ceoName="ceoName" phone="phone" faxNumber="faxNumber" email="email" marginRate="marginRate" address="address" addressDetails="addressDetails" accountNumber="accountNumber" bankName="bankName" beneficiary="beneficiary" paymentTerms="paymentTerms" isActive="isActive" primaryDeliveryLocation="primaryDeliveryLocation" secondaryDeliveryLocation="secondaryDeliveryLocation" salesRepresentative="salesRepresentative" companyIssues="companyIssues" 
```
- sales
```
 http :8088/orders orderId="orderId" orderType="orderType" customer="customer" orderDate="orderDate" deliveryDate="deliveryDate" orderManager="orderManager" accountingYearMonth="accountingYearMonth" registeredBy="registeredBy" memo="memo" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

