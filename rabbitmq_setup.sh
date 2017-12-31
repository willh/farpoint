# firstly install pre-requisites of erlang (from source - AMI version is too old) 
# and then install latest rabbitmq
# https://www.freshlex.com/install-rabbitmq-and-minimal-erlang-on-amazon-linux.html
# go make a cup of tea, the `make` command takes a while 
# now configure it
sudo rabbitmq-plugins enable rabbitmq_management

sudo rabbitmqctl delete_user guest
sudo rabbitmqctl add_user picard makeitsonumberone
sudo rabbitmqctl set_user_tags picard administrator
sudo rabbitmqctl set_permissions picard ".*" ".*" ".*"