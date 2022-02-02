# Simple Android MVVM - One screen sample

In this repository, you will find a gist for what I believe, over time, could turn
into a massive activity. 


## Specs

The activity in this example lists places fetched from a REST API and presents it to
the user, once a button is clicked.

### I think about these things when I design my solution

* Testability
* Dependencies
* Separation of responsibilities
* Readability
* Maintainability

## Data models

### Places response

The places response is a json object with the following structure:

```json
{
    "place": "array",
    "total": "number"
}
```

### Place

A place is a json object with the following structure:

```json
{
    "alias": "string",
    "name": "string",
    "longitude": "number",
    "latitude": "number",
    "description": "string",
    "icon": "string",
    "website": "string"
}
```

### JSON response
```json
{
  "place": [
    {
      "alias": "a353a831-5d53-4cc2-93f3-6c8a3df1e3c9",
      "name": "Testplats för MO",
      "longitude": 17.6422735,
      "latitude": 59.8581518,
      "description": "Kommer inom kort",
      "icon": "/image/83423179e3a34332857e47f7cacdf703_icon_1416923254311"
    },
    {
      "alias": "d2ee4e66-cfff-4522-a999-99b0460c535b",
      "name": "Arlanda - Pontus in the Air",
      "longitude": 17.931236799999965,
      "latitude": 59.6513424,
      "description": "Kommande",
      "icon": "/image/db3f9210-d1dd-4b37-bb83-e23ebe8b422c"
    },
    {
      "alias": "b773c20c-5038-4d0e-a216-a647fab1d1a2",
      "name": "Evenemangsgatan 31",
      "longitude": 17.9996718,
      "latitude": 59.3704332,
      "description": ".",
      "icon": "/image/db3f9210-d1dd-4b37-bb83-e23ebe8b422c",
      "website": "https://societyworks.com"
    },
    {
      "alias": "d510127e-7bda-4721-b5d7-9e2308f2f90d",
      "name": "SERGEL",
      "longitude": 18.06319099999996,
      "latitude": 59.33394819999999,
      "description": "Eat clean - train mean - get lean",
      "icon": "/image/eb043be7_46f9_4b94_b133_40bddb4c204a_icon_1441092491212",
      "website": "www.reloadsuperfood.se"
    },
    {
      "alias": "e6255fac-4ca2-482e-be71-39e4e76d11a4",
      "name": "Råvara - Luleå, Storgatan",
      "longitude": 22.1418165,
      "latitude": 65.5831446,
      "description": "Tänk på att hålla avstånd och vänta gärna vid bordet eller utomhus på din beställning. Välkommen till nyöppnade Råvara Storgatan!",
      "icon": "/image/38b7c627-7713-48dd-b843-370e26d302b1",
      "color": "#F7B4C6"
    },
    {
      "alias": "cf16942f-870f-4190-9b65-46fb650f553e",
      "name": "Råvara - Piteå, Backcity",
      "longitude": 21.4429995,
      "latitude": 65.3239425,
      "description": "Tänk på att hålla avstånd och vänta gärna utomhus eller i bilen på din beställning. Välkommen!",
      "icon": "/image/38b7c627-7713-48dd-b843-370e26d302b1",
      "color": "#85C378"
    },
    {
      "alias": "50f1b70c-5a42-497f-a49e-e4fed379f3e1",
      "name": "Asian Roxy",
      "longitude": 17.623662400000057,
      "latitude": 59.1974159,
      "description": "Utkörningsvillkor:\nSkriv adress och telefonnummer i Meddelande-rutan under beställningen.\nBeställningen måste uppgå till minst 400 kronor, annars ska mellanskillnaden upp till 400 kronor betalas.\nPga tillagning och leverans rekommenderas beställningar 40 minuter innan.\n\nOmråde: Gäller endast inom Södertälje. Utkörningen sker i samarbete med Taxi Södertälje.",
      "icon": "/image/a02ffb74-65b2-4cc8-b364-57249a44b16a"
    },
    {
      "alias": "e0fdf642-75da-4c6e-ba46-0d26957e3f31",
      "name": "Berliner Kebab",
      "longitude": 18.07106550000003,
      "latitude": 59.3146131,
      "description": "Vårt mål är att servera högkvalitativ tysk kebab, med riktigt bra ingredienser!",
      "icon": "/image/d1d69354-1148-4a9c-bb6e-9f78fb042370"
    },
    {
      "alias": "fa3f1d58-4365-4667-aff7-d6484fe1f689",
      "name": "Libanon Grill \u0026 Meza",
      "longitude": 18.07049710000001,
      "latitude": 59.3149794,
      "description": "En äkta Libanesisk grillrestaurang på Söder, välkommen!",
      "icon": "/image/db229ada-afb3-4f73-b0da-5083ad486d3a"
    },
    {
      "alias": "b832bcb8-f27f-4ce2-acdc-85cae992bed9",
      "name": "Wrapiano",
      "longitude": 18.05858820000003,
      "latitude": 59.3456007,
      "description": "Välkommen till WRAPIANO !\nWRAPIANO erbjuder hemlagade wraps och sallader. På menyn finner du bland annat biff med chilimajonnäs , kyckling med teryaki , fisk, skaldjur samt vegetariskt. \nVåra wraps och sallader serveras med hemmagjord pesto , såser och vår berömda chilimajonnäs. All mat från WRAPIANO lagas från grunden med omsorg och kärlek för din hälsa och njutning . Det är rätter av naturliga råvaror , dessutom är de enkla att ta med.",
      "icon": "/image/1caea18d-6935-4972-bd8c-39c45cf41e4f"
    },
    {
      "alias": "a5aede1f-e950-41d3-95ec-55ebdb8b07a0",
      "name": "Fabriken",
      "longitude": 22.14943859999994,
      "latitude": 65.5836763,
      "description": "Fabriken, Kafé, Bar och Restaurang. Storgatans nya mötesplats.",
      "icon": "/image/77ad39e7-5f97-49d3-ae73-9db5d608dc60"
    },
    {
      "alias": "9f7521f5-b553-4454-a830-d606e489a524",
      "name": "Gabriels Meze Bar",
      "longitude": 18.01471570000001,
      "latitude": 59.33416440000001,
      "description": "Här finner ni blandade läckerheter från det libanesiska köket.\nVarmt välkomna!",
      "icon": "/image/e31b6ca9-3fc3-4706-86b6-d5236cd0dee5"
    },
    {
      "alias": "564d3bc5-0197-49ca-8823-00c31d4a3831",
      "name": "Juicefabriken",
      "longitude": 18.0338706,
      "latitude 2022-01-29 21:06:56.008 6854-7386/com.close.svea.refactoring_sample I/okhttp.OkHttpClient: n": "Vi erbjuder mat lagad med kärlek i en trevlig miljö. Hos oss får du en härlig upplevelse och den personliga servicen tillsammans med vällagad mat är viktiga ingredienser hos oss. Vi gör allt för att du som gäst skall trivas!",
      "icon": "/image/deadd4f8-544f-4f0b-a798-41f7100851dc"
    },
    {
      "alias": "6d80667f-fee7-43d4-96fa-e176fa9722e2",
      "name": "Panini K25",
      "longitude": 18.06741349999993,
      "latitude": 59.33583599999999,
      "description": "Vi skapar internationella matupplevelser, lagade med omsorg på noggrant utvalda råvaror",
      "icon": "/image/220b1ba3-3666-4f56-b5a8-8d26b8171c2c"
    },
    {
      "alias": "afd40010-93ec-452f-a51e-655053027cb8",
      "name": "Ho\u0027s Kina Restaurang",
      "longitude": 18.03256190000002,
      "latitude": 59.3150066,
      "description": "Hos Ho’s Kina upplever du riktigt autentisk kinamat i en familjär atmosfär. Vår mat gav oss pris för bästa kinesiska krog i Europa på Asian Curry Awards 2014. Här frossar du i rikliga portioner med rustik kinesisk mat med inspiration från",
      "icon": "/image/f34a62c6-4927-45cb-9d03-3419da0a82c0"
    },
    {
      "alias": "7a8b220d-8b43-4216-acba-9d6da3e4ee6b",
      "name": "Ho\u0027s Thai Restaurang",
      "longitude": 18.033151299999986,
      "latitude": 59.3150804,
      "description": "På Ho’s Thai hittar du samma rustika thaimat som människorna ute på landsbygden i nordöstra Thailand äter. Här äter du fräsch och autentisk thaimat med rena och ärliga smaker.",
      "icon": "/image/6d46d065-4408-4461-9c38-b7ad3b7bc076"
    },
    {
      "alias": "2d9dd35c-ec64-4424-8eab-fc9a7b3ba011",
      "name": "SVEAVÄGEN",
      "longitude": 18.059458800000016,
      "latitude": 59.3402142,
      "description": "Eat clean - train mean - get lean",
      "icon": "/image/eb043be7_46f9_4b94_b133_40bddb4c204a_icon_1441092491212"
    },
    {
      "alias": "93875233-315a-42b3-aa47-7705456e7600",
      "name": "O\u0027Learys Norrmalm",
      "longitude": 18.065277899999955,
      "latitude": 59.34066139999999,
      "description": "Med hjärtlig service och ett brett sortiment av drycker finns något för varje smak",
      "icon": "/image/b4e70adc_446a_494b_8188_4a7254792586_icon_1435570579340"
    },
    {
      "alias": "fb64760d-49ca-4e09-be33-f9905fbd73bc",
      "name": "Råvara - Luleå, Storheden",
      "longitude": 22.0459503624572,
      "latitude": 65.6185510070254,
      "description": "Tänk på att hålla avstånd och vänta gärna utomhus eller i bilen på din beställning. Välkommen!",
      "icon": "/image/38b7c627-7713-48dd-b843-370e26d302b1",
      "color": "#F7B4C6"
    },
    {
      "alias": "46aac517-35ef-40c2-856b-1e9331980c8e",
      "name": "Offside Sportsbar",
      "longitude": 17.997845583859316,
      "latitude": 59.36089240658622,
      "description": "Offside Sportsbar",
      "icon": "/image/f9467cde_a180_452b_959e_37f81c5a79bd_icon_1434007813402"
    },
    {
      "alias": "d972ffb3-ad31-476f-b965-422173d2b5cd",
      "name": "Caffè Nero - K25",
      "longitude": 18.06741349999993,
      "latitude": 59.33583599999999,
      "description": "Kvalitet framför kvantitet. \nSlowfood - tänk med råvaror direkt från Italien. Vi gör vår egen pasta (glutenfri pasta finns) Välkomna!",
      "icon": "/image/3ab90fd3-9368-40ac-8689-f41a95666729"
    },
    {
      "alias": "990ac8c7-0c86-4bbe-a8bf-bd809513c11e",
      "name": "Caffé Mezzo",
      "longitude": 17.996379100000013,
      "latitude": 59.3652725,
      "description": "Välkommen in till oss på Caffé Mezzo. Här har vi skapat en lugn och harmonisk atmosfär där du kan få en paus i vardagen och avnjuta god mat, härliga bakverk och självklart riktigt gott kaffe.",
      "icon": "/image/0e335b32_a70d_4f25_8c38_af471a4ca69a_icon_1431697442512"
    },
    {
      "alias": "387174d7-5717-452c-b4ec-42efe2ef2880",
      "name": "Combo - K25",
      "longitude": 18.06741349999993,
      "latitude": 59.33583599999999,
      "description": "“Tolv av världens godaste\nsnabbmatsrätter – och mycket mycket mer.”",
      "icon": "/image/a28b1ff4_9374_43c6_98df_c833b1368c23_icon_1440423753320"
    },
    {
      "alias": "8ff676f3-bd15-4825-9188-bf189009d30c",
      "name": "Grekturken K25",
      "longitude": 18.06741349999993,
      "latitude": 59.33583599999999,
      "description": "Vi är halvgrekplusturk och för oss handlar allt om medelhavets matkultur",
      "icon": "/image/b8a713fa_cdeb_4d3f_9710_df71ddf6787c_icon_1433339104435"
    },
    {
      "alias": "d99554ca-5ba7-4cff-aa86-3ed3f0d394a9",
      "name": "Lebanese Food",
      "longitude": 17.94567600000005,
      "latitude": 59.402259,
      "description": /image/6a26466e_bb27_473d_a106_69bd7741e3a3_icon_1431357973542"}],"total":25}

```
