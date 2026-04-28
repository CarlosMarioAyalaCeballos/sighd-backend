# 🏥 Sistema Inteligente de Gestión Hospitalaria Distribuida (SIGHD)

## 📌 Descripción del Proyecto

El **SIGHD** es una plataforma basada en arquitectura de microservicios diseñada para optimizar la atención en el área de urgencias médicas mediante un sistema inteligente de triaje automático.

El sistema permite:

- Registrar pacientes en urgencias  
- Capturar signos vitales y síntomas  
- Clasificar automáticamente el nivel de gravedad  
- Organizar una cola priorizada de atención  
- Asignar médicos según disponibilidad  
- Registrar diagnósticos en historial clínico  
- Gestionar hospitalización o alta médica  

Opera en un entorno distribuido, lo que garantiza **escalabilidad, alta disponibilidad y tolerancia a fallos**.

---

## 🎯 Objetivo

Mejorar la atención en urgencias hospitalarias mediante:

- Priorización automática de pacientes  
- Reducción de tiempos de espera críticos  
- Optimización del uso de recursos médicos  
- Disminución de errores humanos  
- Garantía de disponibilidad del sistema  

---

## ⚙️ Arquitectura

El sistema está basado en una arquitectura de **microservicios**, compuesta por:

- Frontend Web  
- API Gateway  
- Microservicios:
  - Pacientes  
  - Triaje  
  - Cola de atención  
  - Atención médica  
  - Administración  
- Base de datos distribuida  

---

## 🔐 Requerimientos Clave

### Funcionales
- Gestión de pacientes  
- Registro de triaje (signos vitales y síntomas)  
- Clasificación automática de prioridad  
- Gestión de cola de atención  
- Registro de diagnóstico y tratamiento  
- Administración del sistema  

### No Funcionales
- Seguridad con autenticación y roles  
- Alta disponibilidad (≥ 99%)  
- Respuesta rápida (< 2 segundos)  
- Escalabilidad horizontal  
- Consistencia de datos  
- Interfaz usable e intuitiva  

---

## 🧠 Metodología de Desarrollo

Se utiliza **Scrum**, permitiendo un desarrollo iterativo e incremental mediante Sprints.

### Roles del equipo

- **Product Owner:** Andrea Narvaez  
- **Scrum Master:** Laura Catherine Quintero Vega  
- **Equipo de Desarrollo:**  
  - Estefania Jiménez Peña (Backend)  
  - Andres Felipe Almario Navarro (Backend)  
  - Carlos Mario Ayala Ceballos (Frontend)  

---

## 👥 Integrantes

- Estefania Jiménez Peña  
- Laura Catherine Quintero Vega  
- Andres Felipe Almario Navarro  
- Andrea Narvaez Trujillo  
- Carlos Mario Ayala Ceballos  

---

## 🛠️ Tecnologías (Referenciales)

- Frontend: Angular / React / Vue  
- Backend: Microservicios (Spring Boot sugerido)  
- Base de datos: MySQL distribuido  
- Seguridad: JWT  
- Arquitectura: Hexagonal + Microservicios  

---

## 🔄 Flujo General del Sistema

Usuario → Frontend → API Gateway → Microservicios → Base de Datos  

---

## 📊 Estado del Proyecto

🚧 En desarrollo bajo metodología ágil Scrum.

---

## 📎 Notas Finales

Este sistema está diseñado para entornos hospitalarios con alta demanda, priorizando eficiencia, precisión y disponibilidad en la atención médica.

---