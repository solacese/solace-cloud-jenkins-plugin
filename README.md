# Jenkins Plugin for Solace Cloud
This build plugin for [Jenkins](https://jenkins.io) lets users instantiate services in [Solace PubSub+ Cloud](https://solace.com/products/event-broker/cloud/) as part of their Jenkins workflow.

 

>  	:information_source:
    The plugin has yet to be published to the official Jenkins plugin repository. For the time being, users must manually build & install the plugin into their jenkins   instances.


## Prerequisites
* Jenkins 2.164+
* Maven

## General Build & Install Procedures
### Build
After you clone the repo, execute `mvn clean package`. This will build the plugin to the following location `<path to git repos>/pubsubplus-jenkins.hpi/pubsubplus-jenkins.hpi`.

### Install
Manually install the plugin via `Manage Jenkins -> Manage Plugins -> Advanced -> Upload Plugin` & restart Jenkins.

## Usage
Refer to the plugin's Github Wiki for usage instructions, including basic examples. 


  