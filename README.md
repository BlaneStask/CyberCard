<img align="right" width="140" height="140" src="CyberCard_icon.png">

## CyberCard

CyberCard acts as a convenient way to give out your contact information. Anywhere from business to personal contact information, the CyberCard helps to provide users with their own QR code to be scanned in order to generate a new contact with their provided information. The CyberCard application has a Home, Contact and QR Code page. The Home page provides users with a better understanding of the app. The Contact page allows users to provide information: First Name, Last Name, Company, Phone Number and Email. Once the Contact information has been entered, the user can then save the QR code to their photos through the QR Code page. This allows clients and new friends to scan this QR code to gain their contact information with ease.

## Demo

<p align="center">
<img src="CyberCard_Demonstration.gif" width="280" height="550"/>
</p>

## QR Code Generation

```Java
        MeCard meCard = new MeCard();
        meCard.setName(first_name);
        meCard.setSurname(last_name);
        meCard.setEmail(email);
        meCard.addTelephone(p_number);
        meCard.setOrg(company);

        String meCardContent = meCard.buildString();
        v.setImageBitmap(QRCode.from(meCardContent).bitmap());
```

## Dependencies

```gradle
dependencies {
        implementation group: 'net.glxn.qrgen', name: 'android', version: '2.0'
        implementation 'it.auron:mecard-parser:1.1.5'
}
```

ZXING: [https://github.com/zxing/zxing](https://github.com/zxing/zxing)  
QRGen: [https://github.com/kenglxn/QRGen](https://github.com/kenglxn/QRGen)  
QrCardParsing: [https://github.com/RurioLuca/QrCardParsing](https://github.com/RurioLuca/QrCardParsing)
