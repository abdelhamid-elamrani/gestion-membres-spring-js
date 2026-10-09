resource "aws_instance" "ubuntu" {
  ami           = data.aws_ami.ubuntu.id
  instance_type = "t3.small"

  key_name = aws_key_pair.gest-mm-key.key_name

  vpc_security_group_ids = [
    aws_security_group.gest_mm_sg.id
  ]

  user_data = <<-EOF
                #!/bin/bash
                apt update
                apt install -y docker.io docker-compose-v2
                systemctl enable docker
                systemctl start docker
                EOF
}

data "aws_ami" "ubuntu" {
  # filtres pour chercher l'AMI Ubuntu
  most_recent = true
  owners      = ["099720109477"]
  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }
}

output "ec2_public_ip" {
  value = aws_instance.ubuntu.public_ip
}
