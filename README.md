
# Documentação do Sistema My-Rent

# Visão Geral

O My-Rent é um sistema de gerenciamento de aluguéis de imóveis que visa proporcionar uma solução automatizada para proprietários (ADM) e locatários (moradores). O sistema aborda várias funcionalidades, incluindo o cadastro de proprietários e locatários, registro de imóveis, seleção de planos, gerenciamento de contratos, notificações, segurança e mais.

# Requisitos Funcionais

### Cadastro de Usuários:

1. **Registro de Proprietário (RF01)**:
    - O sistema permite que os ADMs se cadastrem, escolhendo entre quatro planos: teste, básico, intermediário ou premium, cada um com benefícios específicos.
    - Os campos de registro incluem nome completo, e-mail, CPF, telefone (WhatsApp) e endereço residencial.
    - Após o registro, o sistema envia uma confirmação com informações de login via WhatsApp ou e-mail.
    - O sistema oferece sugestões de planos com base no número de imóveis cadastrados e permite que o ADM escolha um plano de preferência.


2. **Registro de Morador (RF02)**:
    - Os ADMs são responsáveis pelo cadastro dos locatários, baseado nos documentos fornecidos na locação.
    - O cadastro inclui informações como nome completo, CPF, telefone (WhatsApp), e-mail (opcional), quantidade de dependentes, data de aluguel e endereço do imóvel.

3. **Registro de Endereço (RF03)**:
    - O sistema permite o cadastro separado de endereços no banco de dados.
    - O sistema não permitirá cadastro de endereço com dados em branco, salvo o item abaixo.
    - O sistema deve permitir que o campo CEP possa ficar vazio e frontend colocará uma string vazia por padrão.
    - O ADM do imóvel faz o registro dos imóveis, fornecendo detalhes como CEP, UF, estado, cidade, bairro, rua e número do imóvel.

4. **Registro de Imóvel (RF04)**:
    - O sistema possibilita o cadastro de imóveis associados aos endereços previamente registrados.
    - Campos de preenchimento incluem endereço, valor do aluguel, quantidade de cômodos, descrição e status de ocupação.

5. **Seleção de Plano (RF05)**:
    - O sistema permite que o ADM selecione um dos quatro planos disponíveis: teste, básico, intermediário e premium.

6. **Tela de Login (RF06)**:
    - A tela de login inclui campos para usuário e senha, um link para redefinir a senha e uma opção para criar um novo cadastro.
    - O campo de senha é ocultado para proteger a privacidade.

7. **Tela de Splash Screen (RF07)**:
    - A tela de Splash Screen é responsável pelo carregamento do banco de dados local e verificação de status de rede.

8. **Tela de Cadastro de Usuário (RF08)**:
    - Existem duas telas de cadastro de usuário: uma para ADM e outra para locatários.
    - O cadastro de ADM requer informações como nome completo, e-mail, CPF, telefone, endereço residencial e quantidade de imóveis.
    - O cadastro de locatário inclui campos como usuário (CPF), senha, nome completo, e-mail, CPF, telefone, endereço do imóvel, quantidade de dependentes e data de aluguel.

9. **Tela Principal ADM (RF09)**:
    - A tela principal do ADM inclui um Dashboard e um Menu Drawer.
    - O Dashboard oferece informações sobre Clientes, Imóveis e Recebimento de Aluguel.
    - O Menu Drawer oferece opções para editar perfil, cadastrar locatários, cadastrar imóveis, cadastrar contratos imobiliários, alterar dados do locatário e sair do sistema.
    - Os usuários podem escolher entre temas claro e escuro.

10. **Tela Principal Locatário (RF10)**:
- A tela principal do locatário também inclui um Dashboard e um Menu Drawer.
- O Dashboard fornece acesso a solicitar manutenção, realizar pagamento via PIX, solicitar confirmação de pagamento, realizar pagamento em dinheiro, verificar extrato de pagamentos e acessar o contrato imobiliário.
- Os locatários também podem escolher entre temas claro e escuro.

11. **Confirmação de Cadastro (RF11)**:
- O sistema envia uma mensagem de confirmação de cadastro com informações de login via WhatsApp ou e-mail, de acordo com a preferência do locatário.

### Registro de Aluguel:

12. **Registro de Aluguel (RF12)**:
- O sistema permite o registro de contratos de aluguel, associando informações sobre o proprietário, endereço, imóvel, morador, valor do aluguel e duração do contrato.
- Os campos obrigatórios incluem nome do proprietário, endereço, imóvel, nome do morador, valor do aluguel e tempo de contrato.

13. **Consulta de Aluguel (RF13)**:
- O sistema permite que ADMs e locatários acessem informações detalhadas sobre os contratos de aluguel existentes.
- ADMs podem consultar todos os aluguéis associados aos imóveis sob sua administração, enquanto os locatários podem visualizar detalhes sobre seus próprios contratos.

14. **Atualização de Aluguel (RF14)**:
- ADMs têm a capacidade de atualizar informações nos contratos de aluguel, como a duração do contrato e o valor do aluguel, se necessário.
- Locatários podem atualizar informações adicionais, como seus próprios dados pessoais.

# Requisitos Não Funcionais

### Requisitos Não Funcionais (Backend):

1. **Integridade de Dados (RNF01)**:
    - O sistema deve garantir a integridade dos dados dos contratos de aluguel, evitando duplicações e inconsistências.

2. **Segurança (RNF02)**:
    - As informações sensíveis nos contratos de aluguel, como valores, devem ser protegidas por medidas de segurança apropriadas.

3. **Escalabilidade (RNF03)**:
    - O sistema deve ser escalável para lidar com um grande número de contratos de aluguel e usuários.

4. **Notificação Automática (RNF04)**:
    - O sistema deve ser capaz de enviar notificações automáticas para ADMs e locatários em eventos como confirmação de cadastro e atualiza


### Objeto User Admin
```json
{
  "type": "ADM",
  "name": "ADM Name",
  "email": "email@example.com",
  "cpf": "123.456.789-00",
  "phone": "(11) 99999-9999",
  "addressEntity": {
    "zipCode": "12345-678",
    "state": "SP",
    "city": "São Paulo",
    "neighborhood": "Test Neighborhood",
    "street": "Sample Street",
    "number": "123"
  },
  "plan": "premium"
}
```

### Objeto User Locatario
```json
{
  "type": "Tenant",
  "name": "Tenant Name",
  "email": "tenant@example.com",
  "cpf": "987.654.321-00",
  "phone": "(11) 88888-8888",
  "addressEntity": {
    "zipCode": "54321-876",
    "state": "SP",
    "city": "São Paulo",
    "neighborhood": "Sample Neighborhood",
    "street": "Test Street",
    "number": "456"
  },
  "dependents": 2,
  "rentDate": "2023-12-01"
}
```

### Objeto User Endereço
```json
{
  "zipCode": "12345-678",
  "state": "SP",
  "city": "São Paulo",
  "neighborhood": "Test Neighborhood",
  "street": "Sample Street",
  "number": "123"
}
```

### Objeto User Imóvel
```json
{
  "addressEntity": {
    "zipCode": "12345-678",
    "state": "SP",
    "city": "São Paulo",
    "neighborhood": "Test Neighborhood",
    "street": "Sample Street",
    "number": "123"
  },
  "rentValue": 2000,
  "rooms": 3,
  "description": "Spacious apartment",
  "occupationStatus": "Occupied"
}
```

### Objeto Rent
```json
{
   "ownerName": "John Doe",
   "addressEntity": "123 Main Street, Apt 4B",
   "property": "Ocean View Condo",
   "residentName": "Jane Smith",
   "rentAmount": 1200.00,
   "contractDuration": 12
}

```