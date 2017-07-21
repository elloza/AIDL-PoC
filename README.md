# AIDL PoC

This is a basic example of the Android Interface Definition Language a component of the Android framework
that allows to separate apps (processes) to communicate with each other using a "contract" (interface).

This is a fork of the afollestad project used to play and learn how to employ this kind of communication in Android.

This PoC will use 3 different applications:

<p align="center">
  <img src="https://github.com/elloza/AIDL-PoC/blob/master/images/AIDL.png">
</p>

- MainApp: This app will interact with the other ones and it will allow the user to transform amounts of money from a selected currency to another one. This service will be exposed only for this application.

[http://fixer.io/](http://fixer.io/)

In addition the user will be able to get information about the introduced number thanks to another application which offers a service to get funny quotes about numbers. That service is offered for all apps in the system.

[NumbersAPI](http://numbersapi.com/)

The application will show a textbox (numeric for the number), 2 Spinners (with currency codes), 2 textboxes for the results and 1 button to perform the requests.

- BankApp: this app will offer a private service with permissions only for MainApp and will provide currency info.

- NamesApp: this app will offer a public service without permissions to provide info about numbers.

- TestApp: this app will test if it is possible get some info from BankApp throught their exposed Service.

This example illustrates the use of permissions in services offered as AIDL.

### Using this example

To observe this example project work, you must first install the `NamesApp`and 'BankApp' modules on your device, 
(it does not show any UI, they are just `Services`). 

Once it's installed, install and run the `MainApp` module on your device. The `MainApp` module will display UI that starts the services, binds with the services, and uses methods declared in the services.


# USE CASE PoC:

An application needs to perform the following use case:

1. Performing a payment through a private AIDL service of a BankAPP. That app will implement several payments options accessed through the same interface. The payment option selected will be in the request with other information.
2. If the payment was successful then a ticket will be printed through a public AIDL service of a PrinterApp. That app will implement different printer's sdk and they will be accessed through the same interface.
3. Any error or success message will be show to the user.

<p align="center">
  <img src="https://github.com/elloza/AIDL-PoC/blob/master/images/AIDL Diagram USE CASE.png">
</p>

The previous design will allow us decouple the own business logic from implementation details.


