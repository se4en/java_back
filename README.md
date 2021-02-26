# Web-приложение для работы с зарплатной ведомостью предприятия
## Часть 1. 

#### Схема базы данных:

![ER-diagram](db/ER.png)

#### Схема навигации между страницами:

![Navigation](Navigation.png)

#### Описание страниц:

1. *Главная страница:*

  Начальная точка использования - содержит ссылки на страницы (2), (3), (4). 

2. *Страница с информацией о сотрудниках:* 

  Основной элемент страницы - список всех сотрудников, в котором отображаются ФИО и должность, с поддержкой поиска по проектам, диапазону з/п, должностям и т.п.
  Возможен переход на главную страницу (1) и по результатам поиска на страницу сотрудника (3).

3. *Страница с информацией о сотруднике:*

  Здесь находится вся доступная информация о сотруднике, с этой страницы можно ее редактировать. Можно добавлять/удалять сотрудников. С этой страницы можно перейти на страницу проекта (5), на страницу     управления операцией (7), также вернуться на страницу (2).

4. *Страница с информацией о проектах:*
  
  Здесь отображается список всех проектов с возможность поиска и фильтрами. Можно создавать/удалять преокты. Можно перейти на страницу (5).

5. *Страница управления проектом:*

  Здесь находится вся информация о проекте с возможностью редактирования. От списка сотрудников, участвующий в проекте, можно перейти на страницу (3).

6. *Страница с информацией об операциях:*

  Здесь также списком приводится информация о последних операциях с возможность создания, поиска, удаления. Для редактирования/создания можно перейти на страницу (7).
  
7. *Страница управления операцией:*

  Здесь находится информация об операции, производится ее редактирование/создание. Можно перейти к списку всех операций (6).

#### Сценарии использования:

1. *Получить список сотрудников, удовлетворяющих определенным требованиям*

  Главная страница (1) -> переходим по [*Сотрудники*] -> Страница с информацией о сотрудниках (2) -> устанавливаем параметры поиска.

2. *Посмотреть историю сотрудника*

  Главная страница (1) -> переходим по [*Сотрудники*] -> Страница с информацией о сотрудниках (2) -> производим поиск по имени или устанавливаем параметры.

3. *Изменение списка участников проекта/изменение роли в проекте*

  Главная страница (1) -> переходим по [*Проекты*] -> Страница с информацией о проектах (4) -> находим необходимый проект -> переходим по [*Информация*] на этом проекте на (5) -> тут есть список сотрудников - можно изменить роль у любого сотрудника или сам список.

4. *Запланировать выплату премии для определенной категории сотрудников*

  Главная страница (1) -> переходим по [*Выплаты*] -> Страница с информацией об операциях (6) -> переходим по [*Добавить операцию*] на страницу (7) -> создаем необходимую выплату и выбираем список сотрудников -> создастся операция для каждого сотрудника из списка.

5. *Посмотреть историю выплат определенному сотруднику(чтобы принять решение о новой выплате)*

  Главная страница (1) -> переходим по [*Сотрудники*] -> Страница с информацией о сотруднике (2) -> в разделе [*История выплат*].
 
6. *Узнать общую сумму выплат сотрудника в этом месяце*

  Главная страница (1) -> переходим по [*Выплаты*] -> Страница с информацией об операциях (6) -> в параметрах поиска устанавливаем временной интервал.
 
